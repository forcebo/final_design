package com.finaldesign.backend.service.impl.student.requirement;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.finaldesign.backend.mapper.RequirementMapper;
import com.finaldesign.backend.pojo.Requirement;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Student;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.student.requirement.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RequirementServiceImpl implements RequirementService {
    @Autowired
    private RequirementMapper requirementMapper;

    @Override
    public Result insertRequirement(Requirement requirement) {
        if (StrUtil.isBlank(requirement.getStudentName())) {
            return Result.fail("联系人不能为空");
        }
        if (!Validator.isMobile(requirement.getPhone())) {
            return Result.fail("请输入正确的手机号");
        }
        String[] areas = {"莲湖区", "新城区", "碑林区", "雁塔区", "灞桥区", "未央区", "阎良区",
        "临潼区", "长安区", "高陵区", "蓝田区", "户县区", "周至区", "高新区", "沣渭新区", "曲江新区", "杨凌"};
        if (StrUtil.isBlank(requirement.getArea())) {
            return Result.fail("请选择你所在的位置地区");
        }
        if (!isValidOptions(requirement.getArea(), areas)) {
            return Result.fail("你所在的地区不存在");
        }
        String[] sex = {"男", "女"};
        if (StrUtil.isBlank(requirement.getSex())) {
            return Result.fail("请选择学生性别");
        }
        if (!isValidOptions(requirement.getSex(), sex)) {
            return Result.fail("学生性别只能为男或女");
        }
        if (StrUtil.isBlank(requirement.getGrade())) {
            return Result.fail("请选择年级");
        }
        String[] grades = {"幼儿", "一年级", "二年级", "三年级", "四年级", "五年级", "六年级", "初一", "初二"
        , "初三", "高一", "高二", "高三", "大一", "大二", "大三", "大四", "自考生", "社会人", "外国人"};
        if (!isValidOptions(requirement.getGrade(), grades)) {
            return Result.fail("请重新选择年级");
        }
        if (StrUtil.isBlank(requirement.getSubject())) {
            return Result.fail("请填写求教科目");
        }
        if (StrUtil.isBlank(requirement.getTeacherSex())) {
            return Result.fail("请选择要求的教员性别");
        }
        String[] teacherSex = {"男", "女", "均可"};
        if (!isValidOptions(requirement.getSex(), teacherSex)) {
            return Result.fail("要求的教员性别只能为男或女或均可");
        }
        String[] modes = {"上门辅导", "网络辅导", "均可"};
        if (StrUtil.isBlank(requirement.getMode())) {
            return Result.fail("请选择辅导方式");
        }
        if (!isValidOptions(requirement.getMode(), modes)) {
            return Result.fail("辅导方式只能为上面辅导或网络辅导或者均可");
        }
        if (StrUtil.isBlank(requirement.getCharge())) {
            return Result.fail("请填入你的收费要求");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        requirement.setStudentId(student.getId());

        requirementMapper.insert(requirement);
        return Result.ok();
    }

    public boolean isValidOptions(String option, String[] validOptions) {
        boolean isValidEducation = false;
        for (String o: validOptions) {
            if (o.equals(option)) {
                isValidEducation = true;
                break;
            }
        }
        return isValidEducation;
    }
}
