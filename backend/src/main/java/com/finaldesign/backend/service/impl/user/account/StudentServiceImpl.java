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
import com.mysql.cj.util.StringUtils;
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
import java.io.File;
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
        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配");
        }

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
        if (!Validator.isMobile(phone)) {
            return Result.fail("请输入正确的手机号");
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

    @Override
    public Result updateInfo(Map<String, String> map) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();

        String username = map.get("username");
        String realname = map.get("realname");
        String phone = map.get("phone");
        String address = map.get("address");
        if (StrUtil.isBlank(username)) {
            return Result.fail("用户名不能为空");
        }
        if(StrUtil.isBlank(realname)) {
            return Result.fail("请填入真实姓名");
        }
        if (!Validator.isMobile(phone)) {
            return Result.fail("请输入正确的手机号");
        }
        if (StrUtil.isBlank(address)) {
            return Result.fail("通讯地址不能为空");
        }

        if (!username.equals(student.getUsername())) {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            List<Student> users = studentMapper.selectList(queryWrapper);
            if (!users.isEmpty()) {
                return Result.fail("用户名已经存在");
            }
        }
        if (!phone.equals(student.getPhone())) {
            QueryWrapper<Student> phoneQueryWrapper = new QueryWrapper<>();
            phoneQueryWrapper.eq("phone", phone);
            List<Student> users2 = studentMapper.selectList(phoneQueryWrapper);
            if (!users2.isEmpty()) {
                return Result.fail("手机号已存在");
            }
        }
        Student new_student = new Student(student.getId(), username, student.getPassword(), student.getPhoto(), realname,
                student.getSex(), phone, address);
        studentMapper.updateById(new_student);

        return Result.ok();
    }

    @Override
    public Result updatePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        if(StrUtil.isBlank(oldPassword)) {
            return Result.fail("请输入原密码");
        }
        if (StrUtil.isBlank(newPassword)) {
            return Result.fail("修改后的密码不能为空");
        }
        if (!newPassword.equals(confirmNewPassword)) {
            return Result.fail("两次修改后的密码不相同");
        }
        if (!passwordEncoder.matches(oldPassword, student.getPassword())) {
            return Result.fail("用户原密码错误");
        } else {
            student.setPassword(passwordEncoder.encode(newPassword));
            studentMapper.updateById(student);
            return Result.ok();
        }
    }

    @Override
    public Result updatePhoto(String photo) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        String oldPhotoUrl = student.getPhoto();
        if (!StrUtil.isBlank(oldPhotoUrl)) {
            String path = oldPhotoUrl.replaceFirst("^https?://[^/]+", "");
            String oldFileUrl = "D:/" + path;
            File oldFile =  new File(oldFileUrl);
            if (oldFile.exists()) {
                boolean deleted = oldFile.delete();
                if (!deleted) {
                    System.out.println("删除之前的图片文件失败，请手动删除");
                }
            }
        }
        student.setPhoto(photo);
        studentMapper.updateById(student);
        return Result.ok(photo);
    }
}
