package com.finaldesign.backend.service.teacher.course;

import com.finaldesign.backend.pojo.Result;

public interface CourseService {
    Result getCoursesByTeacherId(Integer teacherId);

    Result getCoursesById(Integer id);

    Result getAllCourses(Integer page);

    Result examineYesById(Integer id);
    Result examineNotById(Integer id);
    Result uploadCourse(String courseName, String description, String imageFile, String location,
                        String videoFile, String price);
}
