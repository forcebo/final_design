package com.finaldesign.backend.controller.student.reserve;

import com.finaldesign.backend.pojo.Reserve;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.reserve.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @PostMapping("/student/reserve/")
    public Result reserveTeacher(@RequestBody Reserve reserve) {
        if (reserve == null) {
            return Result.fail("预约失败");
        }
        return reserveService.reserveTeacher(reserve);
    }

    @GetMapping("/student/reserve/get/")
    public Result getReservesByStudentId() {
        return reserveService.getReservesByStudentId();
    }

    @GetMapping("/student/reserve/check/{teacherId}/")
    public Result checkIfReserve(@PathVariable Integer teacherId) {
        return reserveService.checkIfReserve(teacherId);
    }

    @GetMapping("/student/reserve/cancel/{teacherId}/")
    public Result cancelReserve(@PathVariable Integer teacherId) {
        return reserveService.cancelReserve(teacherId);
    }

    @GetMapping("/admin/statistics/teacher/reserves/top/5/")
    public Result getTop5TeacherForReserveStatistics() {
        return reserveService.getTop5TeacherForReserveStatistics();
    }

    @GetMapping("/teacher/get/reserves/")
    public Result getReservesByTeacherId( @RequestParam Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page <= 0) {
            return Result.fail("获取家教需求失败");
        }
        return reserveService.getReservesByTeacherId(page);
    }
}
