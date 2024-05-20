package com.finaldesign.backend.controller.teacher.CV;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.ConditionQueryTeacher;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.teacher.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CVController {
    @Autowired
    private CVService cvService;

    @PostMapping("/teacher/release/job/information/")
    private Result releaseJobInformation(@RequestBody CV cv) {
        return cvService.insertCV(cv);
    }

    @PostMapping("/teacher/get/job/information/")
    private Result getJobInformation(){
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
}
