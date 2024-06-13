package com.finaldesign.backend.service.impl.teacher.course;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finaldesign.backend.mapper.CourseMapper;
import com.finaldesign.backend.pojo.*;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.impl.utils.TeacherDetailsImpl;
import com.finaldesign.backend.service.teacher.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
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

    @Override
    public Result getAllCourses(Integer page) {
        IPage<Course> recordPage = new Page<>(page, 10);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Course> records = courseMapper.selectPage(recordPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        List<JSONObject> items = new LinkedList<>();
        for(Course course : records) {
            JSONObject item = new JSONObject();
            item.put("id", course.getId());
            item.put("name", course.getName());
            item.put("description", course.getDescription());
            item.put("teacherId", course.getTeacherId());
            item.put("thumbnail", course.getThumbnail());
            item.put("videoUrl", course.getVideoUrl());
            item.put("area", course.getArea());
            item.put("price", course.getPrice());
            item.put("status", course.getStatus());
            item.put("isExamine", course.getIsExamine());
            items.add(item);
        }
        resp.put("records", items);
        resp.put("total_records", courseMapper.selectCount(null));
        return Result.ok(resp);
    }

    @Override
    public Result examineYesById(Integer id) {
        Course course = courseMapper.selectById(id);
        if (course != null) {
            course.setIsExamine(1);
            course.setStatus(1);
            courseMapper.updateById(course);
        }
        return Result.ok();
    }

    @Override
    public Result examineNotById(Integer id) {
        Course course = courseMapper.selectById(id);
        if (course != null) {
            course.setIsExamine(1);
            course.setStatus(0);
            courseMapper.updateById(course);
        }
        return Result.ok();
    }

    @Override
    public Result uploadCourse(String courseName, String description, String imageFile, String location, String videoFile, String price) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof TeacherDetailsImpl)) {
            return Result.fail("token不匹配");
        }
        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authenticationToken.getPrincipal();
        Teacher teacher = loginUser.getTeacher();
        courseMapper.insert(new Course(null, courseName, description, teacher.getId(), imageFile,
                videoFile, location, new BigDecimal(price), 0, 0));
        return Result.ok();
    }
}
