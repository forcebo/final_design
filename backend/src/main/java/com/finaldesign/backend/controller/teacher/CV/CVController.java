package com.finaldesign.backend.controller.teacher.CV;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.ConditionQueryTeacher;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.teacher.cv.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CVController {
    @Autowired
    private CVService cvService;

    @PostMapping("/teacher/release/job/information/")
    public Result releaseJobInformation(@RequestBody CV cv) {
        return cvService.insertCV(cv);
    }

    @PostMapping("/teacher/get/job/information/")
    public Result getJobInformation(){
        return cvService.getJobInformation();
    }

    @PostMapping("/teacher/getTeacherList/{currentPage}/")
    public Result getTeacherList(@PathVariable Integer currentPage, @RequestBody ConditionQueryTeacher condition) {
        return cvService.multipleTablesAndCondition(currentPage, condition);
    }

    @GetMapping("/student/get/teacher/info/{id}")
    public Result getTeacherInfo(@PathVariable Integer id) {
        return cvService.getTeacherInfoById(id);
    }

    @GetMapping("/student/get/teacher/new/")
    public Result getTeacherNew(){
        return cvService.getTeacherNew();
    }

    @GetMapping("/admin/get/all/job/infomation/")
    public Result getAllJobInformation(@RequestParam Map<String,String> data){
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page < 1) {
            return Result.fail("获取求职信息失败");
        }
        return cvService.getAllCV(page);
    }

    @PostMapping("/admin/examine/job/infomation/yes/{id}/")
    public Result examineYesById(@PathVariable Integer id) {
        return cvService.examineYesById(id);
    }

    @PostMapping("/admin/examine/job/infomation/not/{id}/")
    public Result examineNotById(@PathVariable Integer id) {
        return cvService.examineNotById(id);
    }
}
