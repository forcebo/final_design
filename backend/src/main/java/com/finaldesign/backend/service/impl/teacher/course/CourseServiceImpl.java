package com.finaldesign.backend.service.impl.teacher.course;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finaldesign.backend.mapper.CourseMapper;
import com.finaldesign.backend.pojo.Course;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.teacher.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Result getCoursesByTeacherId(Integer teacherId) {
        if (teacherId == null || teacherId <= 0) {
            return Result.fail("错误");
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId).eq("status", 1);
        List<Course> courses = courseMapper.selectList(queryWrapper);
        return Result.ok(courses);
    }

    @Override
    public Result getCoursesById(Integer id) {
        if (id == null || id <= 0) {
            return Result.fail("错误");
        }
        Course course = courseMapper.selectById(id);
        return Result.ok(course);
    }
}
