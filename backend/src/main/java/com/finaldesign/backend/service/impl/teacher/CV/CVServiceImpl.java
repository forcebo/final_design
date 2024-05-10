package com.finaldesign.backend.service.impl.teacher.CV;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.finaldesign.backend.Mapper.CVMapper;
import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Teacher;
import com.finaldesign.backend.service.impl.utils.TeacherDetailsImpl;
import com.finaldesign.backend.service.teacher.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CVServiceImpl implements CVService {
    @Autowired
    private CVMapper cvMapper;

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
}
