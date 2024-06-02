package com.finaldesign.backend.controller.teacher.course;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.teacher.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
}
