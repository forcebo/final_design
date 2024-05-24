package com.finaldesign.backend.service.impl.teacher.cv;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finaldesign.backend.mapper.CVMapper;
import com.finaldesign.backend.mapper.TeacherMapper;
import com.finaldesign.backend.pojo.*;
import com.finaldesign.backend.service.impl.utils.TeacherDetailsImpl;
import com.finaldesign.backend.service.teacher.cv.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CVServiceImpl implements CVService {
    @Autowired
    private CVMapper cvMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Result insertCV(CV cv) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof TeacherDetailsImpl)) {
            return Result.fail("token不匹配");
        }
        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authenticationToken.getPrincipal();
        Teacher teacher = loginUser.getTeacher();
        if (StrUtil.isBlank(cv.getSubjects()) || !ReUtil.isMatch("^([^,]+,)*([^,]+)?$", cv.getSubjects())) {
            return Result.fail("可教授科目必须是以,分隔的格式, 且不能为空");
        }
        if (StrUtil.isBlank(cv.getDescription())) {
            return Result.fail("自我描述不能为空");
        }
        if(StrUtil.isBlank(cv.getAreas()) || !ReUtil.isMatch("^([^,]+,)*([^,]+)?$", cv.getAreas())) {
            return Result.fail("可授课区域必须是以,分隔的格式，且不能为空");
        }
        if (StrUtil.isBlank(cv.getMode())) {
            return Result.fail("辅导方式不能为空");
        }
        if (StrUtil.isBlank(cv.getSalary())) {
            return Result.fail("薪水要求不能为空");
        }

        cv.setTeacherId(teacher.getId());

        cvMapper.insert(cv);

        return Result.ok();
    }

    @Override
    public Result getJobInformation() {
        return null;
    }

    @Override
    public Result multipleTablesAndCondition(Integer currentPage, ConditionQueryTeacher condition) {
        if (currentPage == null || currentPage < 1) {
            return Result.fail("请输入正确参数");
        }
        Page<TeacherInfo> pageInfo = new Page<>(currentPage, 10);
        Page<TeacherInfo> res = cvMapper.findAndPage(pageInfo, condition);
        long total = res.getTotal();
        List<TeacherInfo> teachers = res.getRecords();
        JSONObject resp = new JSONObject();
        resp.put("teachers", teachers);
        resp.put("total_teachers", total);
        return Result.ok(resp);
    }

    @Override
    public Result getTeacherInfoById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail("教师不存在");
        }
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<CV> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", id);
        CV cv = cvMapper.selectOne(queryWrapper);
        jsonObject.put("cv", cv);
        Teacher teacher = teacherMapper.selectById(id);
        jsonObject.put("teacher", teacher);

        return Result.ok(jsonObject);
    }
}
