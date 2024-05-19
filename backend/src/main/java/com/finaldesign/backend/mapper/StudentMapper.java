package com.finaldesign.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finaldesign.backend.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
