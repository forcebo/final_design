package com.finaldesign.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finaldesign.backend.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
