package com.finaldesign.backend.service.impl.student.reserve;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finaldesign.backend.mapper.ReserveMapper;
import com.finaldesign.backend.pojo.Reserve;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Student;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.student.reserve.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private ReserveMapper reserveMapper;

    @Override
    public Result reserveTeacher(Reserve reserve) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配, 请注册学生用户然后再预约教师");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        if (reserve.getTeacherId() == null || reserve.getTeacherId() <= 0) {
            return Result.fail("预约失败");
        }
        QueryWrapper<Reserve> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", student.getId()).eq("teacher_id", reserve.getTeacherId());
        List<Reserve> reserveList = reserveMapper.selectList(queryWrapper);
        if (!reserveList.isEmpty()) {
            return Result.fail("你已经预约过该教员了");
        }
        if (StrUtil.isBlank(reserve.getName())) {
            return Result.fail("请输入联系人");
        }
        if (!Validator.isMobile(reserve.getPhone())) {
            return Result.fail("请输入正确的手机号");
        }
        if (StrUtil.isBlank(reserve.getArea())) {
            return Result.fail("请输入地区");
        }
        if (StrUtil.isBlank(reserve.getStudentSex())) {
            return Result.fail("请输入学生性别");
        }
        if (StrUtil.isBlank(reserve.getStudentGrade())) {
            return Result.fail("请输入学生年龄");
        }
        if (StrUtil.isBlank(reserve.getSubject())) {
            return Result.fail("请输入求教科目");
        }
        if (StrUtil.isBlank(reserve.getClassTime())) {
            return Result.fail("请输入可上课时间");
        }
        if (StrUtil.isBlank(reserve.getMode())) {
            return Result.fail("请选择上课方式");
        }
        if (StrUtil.isBlank(reserve.getCharge())) {
            return Result.fail("请输入收费要求");
        }
        reserve.setStudentId(student.getId());
        reserveMapper.insert(reserve);
        return Result.ok();
    }

    @Override
    public Result getReservesByStudentId() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配, 请注册学生用户然后再预约教师");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();

        QueryWrapper<Reserve> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", student.getId());
        List<Reserve> reserves = reserveMapper.selectList(queryWrapper);
        return Result.ok(reserves);
    }

    @Override
    public Result checkIfReserve(Integer teacherId) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配, 请注册学生用户然后再预约教师");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        if (teacherId == null || teacherId <= 0 ) {
            return Result.fail("错误");
        }
        QueryWrapper<Reserve> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", student.getId()).eq("teacher_id", teacherId);
        List<Reserve> reserves = reserveMapper.selectList(queryWrapper);
        if (reserves.isEmpty()) {
            return Result.ok(false); // 未预约
        } else {
            return Result.ok(true); // 已预约
        }
    }

    @Override
    public Result cancelReserve(Integer teacherId) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配, 请注册学生用户然后再预约教师");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        if (teacherId == null || teacherId <= 0) {
            return Result.fail("取消预约失败");
        }
        QueryWrapper<Reserve> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", student.getId());
        queryWrapper.eq("teacher_id", teacherId);
        reserveMapper.delete(queryWrapper);
        return Result.ok();
    }
}
