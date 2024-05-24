package com.finaldesign.backend.service.teacher.course;

import com.finaldesign.backend.pojo.Result;

public interface CourseService {
    Result getCoursesByTeacherId(Integer teacherId);

    Result getCoursesById(Integer id);
}
