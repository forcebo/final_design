package com.finaldesign.backend.service.impl.user.account;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finaldesign.backend.mapper.StudentMapper;
import com.finaldesign.backend.mapper.TeacherMapper;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Student;
import com.finaldesign.backend.pojo.Teacher;
import com.finaldesign.backend.service.impl.utils.TeacherDetailsImpl;
import com.finaldesign.backend.service.user.account.UserService;
import com.finaldesign.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements UserService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Autowired
    private DaoAuthenticationProvider teacherAuthenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result getInfo() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authenticationToken.getPrincipal();
        Teacher teacher  = loginUser.getTeacher();

        return Result.ok(teacher);
    }

    @Override
    public Result getToken(String username, String password) {
        if (Validator.isMobile(username)) {
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", username);
            List<Teacher> users = teacherMapper.selectList(queryWrapper);
            if (users.isEmpty()) {
                return Result.fail("手机号不存在或未注册");
            } else {
                username = users.get(0).getUsername();
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = teacherAuthenticationProvider.authenticate(authenticationToken); // 登录失败， 会自动处理

        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authentication.getPrincipal();
        Teacher teacher = loginUser.getTeacher();
        String jwt = JwtUtil.createJWT(teacher.getId().toString(), "TEACHER");

        return Result.ok(jwt);
    }

    @Override
    public Result register(Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String confirmedPassword = map.get("confirmedPassword");
        String identity = map.get("identity");
        String realname = map.get("realname");
        String sex = map.get("sex");
        String age = map.get("age");
        String education = map.get("education");
        String school = map.get("school");
        String major = map.get("major");
        String phone = map.get("phone");
        String address = map.get("address");
        String is_agree = map.get("is_agree");
        String city = map.get("city");
        city = "西安";

        if (StrUtil.isBlank(username)) {
            return Result.fail("用户名不能为空");
        }
        if (StrUtil.isBlank(password) || StrUtil.isBlank(confirmedPassword)) {
            return Result.fail("密码或确认密码不能为空");
        }
        String[] allowedIdentity = {"在校大学生(研究生),不含外籍留学生", "专业老师（注册教师应符合相关政策法规）", "其他(已经毕业离校的人员)"};
        if (!isValidOptions(identity, allowedIdentity)) {
            return Result.fail("请选择正确的身份");
        }
        if (StrUtil.isBlank(is_agree) || "0".equals(is_agree)) {
            return Result.fail("请点击同意注册条款");
        }
        if(StrUtil.isBlank(realname)) {
            return Result.fail("请填入真实姓名");
        }
        if (StrUtil.isBlank(sex)) {
            return Result.fail("性别不能为空");
        }
        if (StrUtil.isBlank(age)) {
            return Result.fail("请填入年龄");
        }
        String[] allowedEducation = {"大专以下", "大专在读", "大专毕业", "本科在读", "本科毕业", "硕士在读", "硕士毕业", "博士在读", "博士毕业"};
        if (!isValidOptions(education, allowedEducation)) {
            return Result.fail("请选择正确的学历");
        }
        if (StrUtil.isBlank(school)) {
            return Result.fail("请输入你的就读或毕业学校");
        }
        if (StrUtil.isBlank(major)) {
            return Result.fail("请填入你的专业");
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

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<Teacher> users = teacherMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            return Result.fail("用户名已经存在");
        }

        QueryWrapper<Teacher> phoneQueryWrapper = new QueryWrapper<>();
        phoneQueryWrapper.eq("phone",phone);
        List<Teacher> users2 = teacherMapper.selectList(phoneQueryWrapper);
        if (!users2.isEmpty()) {
            return Result.fail("手机号不能重复注册");
        }
        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/160348_lg_916a3c928b.jpg";
        Teacher teacher = new Teacher(null, username, encodedPassword, identity, realname, sex, Integer.parseInt(age), education, school, major,
                phone, address, photo, city);

        teacherMapper.insert(teacher);

        return Result.ok(teacher);
    }

    @Override
    public Result updateInfo(Map<String, String> map) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof TeacherDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authenticationToken.getPrincipal();
        Teacher teacher = loginUser.getTeacher();

        String username = map.get("username");
        String identity = map.get("identity");
        String realname = map.get("realname");
        String age = map.get("age");
        String education = map.get("education");
        String major = map.get("major");
        String phone = map.get("phone");
        String address = map.get("address");
        String description = map.get("description");

        if(StrUtil.isBlank(username)) {
            return Result.fail("用户名不能改为空");
        }
        if (StrUtil.isBlank(identity)) {

        }
        if (StrUtil.isBlank(realname)) {
            return Result.fail("真实姓名不能改为空");
        }
        if (StrUtil.isBlank(age)) {
            return Result.fail("年龄不能为空");
        }
        if (StrUtil.isBlank(education)) {
            return Result.fail("学历不能为空");
        }
        if (StrUtil.isBlank(major)) {
            return Result.fail("专业不能为空");
        }
        if(!Validator.isMobile(phone)) {
            return Result.fail("请填入正确的手机号");
        }
        if (StrUtil.isBlank(address)) {
            return Result.fail("地址不能为空");
        }
        if (!username.equals(teacher.getUsername())) {
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            List<Teacher> users = teacherMapper.selectList(queryWrapper);
            if (!users.isEmpty()) {
                return Result.fail("用户名已经存在");
            }
        }
        if (!phone.equals(teacher.getPhone())) {
            QueryWrapper<Teacher> phoneQueryWrapper = new QueryWrapper<>();
            phoneQueryWrapper.eq("phone", phone);
            List<Teacher> users2 = teacherMapper.selectList(phoneQueryWrapper);
            if (!users2.isEmpty()) {
                return Result.fail("手机号已存在");
            }
        }
        Teacher new_teacher = new Teacher(teacher.getId(), username, teacher.getPassword(), identity, realname
        , teacher.getSex(), Integer.parseInt(age), education, teacher.getSchool(), major, phone, address, description, teacher.getCity());

        teacherMapper.updateById(new_teacher);

        return Result.ok();
    }

    @Override
    public Result updatePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof TeacherDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authenticationToken.getPrincipal();
        Teacher teacher = loginUser.getTeacher();

        if (StrUtil.isBlank(oldPassword)) {
            return Result.fail("请输入原密码");
        }
        if (StrUtil.isBlank(newPassword)) {
            return Result.fail("修改后的密码不能为空");
        }
        if (StrUtil.isBlank(confirmNewPassword)) {
            return Result.fail("两次修改后的密码不相同");
        }
        if (!newPassword.equals(confirmNewPassword)) {
            return Result.fail("两次修改后的密码不相同");
        }

        if (!passwordEncoder.matches(oldPassword, teacher.getPassword())) {
            return Result.fail("用户原密码错误");
        } else {
            teacher.setPassword(passwordEncoder.encode(newPassword));
            teacherMapper.updateById(teacher);
            return Result.ok();
        }
    }

    @Override
    public Result updatePhoto(String photo) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof TeacherDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authenticationToken.getPrincipal();
        Teacher teacher = loginUser.getTeacher();
        String oldPhotoUrl = teacher.getPhoto();
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
        teacher.setPhoto(photo);
        teacherMapper.updateById(teacher);
        return Result.ok(photo);
    }

    @Override
    public Result getAll(Integer page) {
        IPage<Teacher> recordPage = new Page<>(page, 10);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Teacher> records = teacherMapper.selectPage(recordPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        List<JSONObject> items = new LinkedList<>();
        for(Teacher teacher : records) {
            JSONObject item = new JSONObject();
            item.put("id", teacher.getId());
            item.put("username", teacher.getUsername());
            item.put("photo", teacher.getPhoto());
            item.put("identity", teacher.getIdentity());
            item.put("realname", teacher.getRealname());
            item.put("school", teacher.getSchool());
            item.put("sex", teacher.getSex());
            item.put("age", teacher.getAge());
            item.put("education", teacher.getEducation());
            item.put("major", teacher.getMajor());
            item.put("phone", teacher.getPhone());
            item.put("address", teacher.getAddress());
            items.add(item);
        }
        resp.put("teachers", items);
        resp.put("total_teachers", teacherMapper.selectCount(null));
        return Result.ok(resp);
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
