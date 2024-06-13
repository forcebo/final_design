package com.finaldesign.backend.controller.teacher.course;

import cn.hutool.core.util.StrUtil;
import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.teacher.course.CourseService;
import com.finaldesign.backend.utils.ImgRegulation;
import com.finaldesign.backend.utils.VideoRegulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/teacher/course/get/{id}/")
    public Result getCoursesByTeacherId(@PathVariable Integer id) {
        return courseService.getCoursesByTeacherId(id);
    }

    @GetMapping("/course/get/{id}/")
    public Result getCourseById(@PathVariable Integer id) {
        return courseService.getCoursesById(id);
    }

    @GetMapping("/admin/get/all/course/")
    public Result getAllJobInformation(@RequestParam Map<String,String> data){
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page < 1) {
            return Result.fail("获取求职信息失败");
        }
        return courseService.getAllCourses(page);
    }

    @PostMapping("/admin/examine/course/yes/{id}/")
    public Result examineYesById(@PathVariable Integer id) {
        return courseService.examineYesById(id);
    }

    @PostMapping("/admin/examine/course/not/{id}/")
    public Result examineNotById(@PathVariable Integer id) {
        return courseService.examineNotById(id);
    }

    private final String UPLOAD_DIR = "D:\\videos\\";
    @PostMapping("/teacher/account/upload/course/")
    public Result uploadCourse(@RequestParam("courseName") String courseName,
                               @RequestParam("description") String description,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               @RequestParam("location") String location,
                               @RequestParam("videoFile") MultipartFile videoFile,
                               @RequestParam("price") String price){
        if (StrUtil.isBlank(imageFile.toString()) || StrUtil.isBlank(videoFile.toString())) {
            return Result.fail("课程资料上传失败!请重新上传");
        }
        if (StrUtil.isBlank(courseName)) {
            return Result.fail("课程名字不能为空");
        }
        if (StrUtil.isBlank(description)) {
            return Result.fail("课程描述不能为空");
        }
        if (StrUtil.isBlank(location)) {
            return Result.fail("上课地址不能为空");
        }
        if (StrUtil.isBlank(price)) {
            return Result.fail("课程价格不能为空");
        }
        Date date = new Date();
        String dateForm = new SimpleDateFormat("yyyy-MM").format(date);
        String casePath = UPLOAD_DIR + dateForm;
        String imgFormat = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));
        String videoFormat = videoFile.getOriginalFilename().substring(videoFile.getOriginalFilename().lastIndexOf("."));
        ImgRegulation imgRegulation = new ImgRegulation();
        try{
            boolean ifimg = imgRegulation.VERIFY(imgFormat);
            if(!ifimg) return Result.fail("图片类型错误");

        }catch (Exception e){
            return Result.fail("请重试");
        }
        VideoRegulation videoRegulation = new VideoRegulation();
        try{
            boolean ifVideo = videoRegulation.verify(videoFormat);
            if(!ifVideo) return Result.fail("视频类型错误");

        }catch (Exception e){
            return Result.fail("请重试");
        }
        File f = new File(casePath);
        try {
            if (!f.exists()) {
                f.mkdirs();
            }
        }catch (Exception e) {
            return Result.fail("请重新上传");
        }
        String imgFileName = UUID.randomUUID().toString() + imgFormat;
        try {
            imageFile.transferTo(new File(casePath + "\\" + imgFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageFileUrl = "http://localhost:3000/videos/" + dateForm + "/" + imgFileName;
        String videoFileName = UUID.randomUUID().toString() + videoFormat;
        try {
            videoFile.transferTo(new File(casePath + "\\" + videoFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String videoFileUrl = "http://localhost:3000/videos/" + dateForm + "/" + videoFileName;
        return courseService.uploadCourse(courseName, description, imageFileUrl, location, videoFileUrl, price);
    }
}
