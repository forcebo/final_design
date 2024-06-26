package com.finaldesign.backend.controller.user.account;

import cn.hutool.core.util.StrUtil;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.user.account.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {

    @PostMapping("/admin/account/token/")
    public Result getToken(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.fail("用户名或密码为空");
        }
        return UserServiceFactory.getUserService("admin").getToken(username, password);
    }

    @GetMapping("/admin/account/info/")
    public Result getInfo() {
        return UserServiceFactory.getUserService("admin").getInfo();
    }
}
