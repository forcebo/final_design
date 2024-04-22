package com.finaldesign.backend.service.impl.user.account;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finaldesign.backend.Mapper.StudentMapper;
import com.finaldesign.backend.config.SecurityConfig;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Student;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.user.account.UserService;
import com.finaldesign.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements UserService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DaoAuthenticationProvider studentAuthenticationProvider;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result getInfo() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student  = loginUser.getStudent();

        return Result.ok(student);
    }

    @Override
    public Result getToken(String username, String password) {
        if (Validator.isMobile(username)) {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", username);
            List<Student> users = studentMapper.selectList(queryWrapper);
            if (users.isEmpty()) {
                return Result.fail("手机号不存在或未注册");
            } else {
                username = users.get(0).getUsername();
            }
        }
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(authenticationToken); // 登录失败， 会自动处理
// 使用学生的认证提供者
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = studentAuthenticationProvider.authenticate(authenticationToken);

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authentication.getPrincipal();
        Student student = loginUser.getStudent();
        String jwt = JwtUtil.createJWT(student.getId().toString(), "STUDENT");

        return Result.ok(jwt);
    }
    @Override
    public Result register(Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String confirmedPassword = map.get("confirmedPassword");
        String realname = map.get("realname");
        String sex = map.get("sex");
        String phone = map.get("phone");
        String address = map.get("address");

        if (StrUtil.isBlank(username)) {
            return Result.fail("用户名不能为空");
        }
        if (StrUtil.isBlank(password) || StrUtil.isBlank(confirmedPassword)) {
            return Result.fail("密码或确认密码不能为空");
        }
        if(StrUtil.isBlank(realname)) {
            return Result.fail("请填入真实姓名");
        }
        if (StrUtil.isBlank(sex)) {
            return Result.fail("性别不能为空");
        }
        if (StrUtil.isBlank(phone)) {
            return Result.fail("电话号码不能为空");
        }
        if (phone.length() != 11) {
            return Result.fail("电话号码长度不是11位");
        }
        if (!isNumeric(phone)) {
            return Result.fail("电话号码不全为数字");
        }
        if (StrUtil.isBlank(address)) {
            return Result.fail("通讯地址不能为空");
        }
        if (username.length() > 100) {
            return Result.fail("用户名长度不能大于100");
        }
        if (password.length() > 100 || confirmedPassword.length() > 100) {
            return Result.fail("密码长度不能大于100");
        }
        if (!password.equals(confirmedPassword)) {
            return Result.fail("两次输入的密码不一致");
        }

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<Student> users = studentMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            return Result.fail("用户名已经存在");
        }

        QueryWrapper<Student> phoneQueryWrapper = new QueryWrapper<>();
        phoneQueryWrapper.eq("phone",phone);
        List<Student> users2 = studentMapper.selectList(phoneQueryWrapper);
        if (!users2.isEmpty()) {
            return Result.fail("手机号不能重复注册");
        }
        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/160348_lg_916a3c928b.jpg";
        Student user = new Student(null, username, encodedPassword, photo, realname, sex, phone, address);

        studentMapper.insert(user);

        return Result.ok(user);
    }

    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // 匹配整数或浮点数形式
    }
}
