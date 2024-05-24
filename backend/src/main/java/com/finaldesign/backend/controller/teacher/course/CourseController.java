package com.finaldesign.backend.controller.teacher.course;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.teacher.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/teacher/course/get/{id}/")
    private Result getCoursesByTeacherId(@PathVariable Integer id) {
        return courseService.getCoursesByTeacherId(id);
    }

    @GetMapping("/course/get/{id}/")
    private Result getCourseById(@PathVariable Integer id) {
        return courseService.getCoursesById(id);
    }
}
