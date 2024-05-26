package com.finaldesign.backend.controller.student.reserve;

import com.finaldesign.backend.pojo.Reserve;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.reserve.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/student/reserve/get/{studentId}/")
    public Result getReservesByStudentId(@PathVariable Integer studentId) {
        return reserveService.getReservesByStudentId(studentId);
    }

    @GetMapping("/student/reserve/check/{teacherId}/")
    public Result checkIfReserve(@PathVariable Integer teacherId) {
        return reserveService.checkIfReserve(teacherId);
    }

    @GetMapping("/student/reserve/cancel/{teacherId}/")
    public Result cancelReserve(@PathVariable Integer teacherId) {
        return reserveService.cancelReserve(teacherId);
    }
}
