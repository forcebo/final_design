package com.finaldesign.backend.service.impl.user.account;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finaldesign.backend.mapper.AdminMapper;
import com.finaldesign.backend.mapper.StudentMapper;
import com.finaldesign.backend.pojo.Admin;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Student;
import com.finaldesign.backend.service.impl.utils.AdminDetailsImpl;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.user.account.UserService;
import com.finaldesign.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements UserService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DaoAuthenticationProvider adminAuthenticationProvider;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result getInfo() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof AdminDetailsImpl)) {
            return Result.fail("token不匹配");
        }

        AdminDetailsImpl loginUser = (AdminDetailsImpl) authenticationToken.getPrincipal();

        Admin admin  = loginUser.getAdmin();

        return Result.ok(admin);
    }

    @Override
    public Result getToken(String username, String password) {
        if (Validator.isMobile(username)) {
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", username);
            List<Admin> users = adminMapper.selectList(queryWrapper);
            if (users.isEmpty()) {
                return Result.fail("手机号不存在或未注册");
            } else {
                username = users.get(0).getUsername();
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = adminAuthenticationProvider.authenticate(authenticationToken);

        AdminDetailsImpl loginUser = (AdminDetailsImpl) authentication.getPrincipal();
        Admin admin = loginUser.getAdmin();
        String jwt = JwtUtil.createJWT(admin.getId().toString(), "ADMIN");

        return Result.ok(jwt);
    }

    @Override
    public Result register(Map<String, String> map) {
        return null;
    }

    @Override
    public Result updateInfo(Map<String, String> map) {
        return null;
    }

    @Override
    public Result updatePassword(String password, String changePassword, String changeConfirmPassword) {
        return null;
    }

    @Override
    public Result updatePhoto(String photo) {
        return null;
    }
}
