package com.finaldesign.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.ConditionQueryTeacher;
import com.finaldesign.backend.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CVMapper extends BaseMapper<CV> {

    Page<TeacherInfo> findAndPage(Page<TeacherInfo> page, ConditionQueryTeacher condition);
}
