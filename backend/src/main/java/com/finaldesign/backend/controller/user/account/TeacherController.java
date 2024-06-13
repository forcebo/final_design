package com.finaldesign.backend.controller.user.account;

import cn.hutool.core.util.StrUtil;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.user.account.UserServiceFactory;
import com.finaldesign.backend.utils.ImgRegulation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
public class TeacherController {

    @PostMapping("/teacher/account/token/")
    public Result getToken(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.fail("用户名或密码为空");
        }
        return UserServiceFactory.getUserService("TEACHER").getToken(username, password);
    }

    @GetMapping("/teacher/account/info/")
    public Result getInfo() {
        return UserServiceFactory.getUserService("TEACHER").getInfo();
    }

    @PostMapping("/teacher/account/register/")
    public Result register(@RequestParam Map<String, String> map) {
        return UserServiceFactory.getUserService("TEACHER").register(map);
    }

    @PostMapping("/teacher/account/password/update/")
    public Result updatePassword(@RequestParam Map<String, String> map) {
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        String confirmNewPassword = map.get("confirmNewPassword");
        return UserServiceFactory.getUserService("TEACHER").updatePassword(oldPassword, newPassword, confirmNewPassword);
    }

    @PostMapping("/teacher/account/photo/update/")
    public Result updatePhoto(@RequestParam("avatar") MultipartFile file) throws IOException {
        if(StrUtil.isBlank(file.toString())) return Result.fail("图片上传失败");
        Date date = new Date();
        String dateForm = new SimpleDateFormat("yyyy-MM").format(date);
        String casePath = "D:\\img\\" + dateForm;
        String imgFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        ImgRegulation regulation = new ImgRegulation();
        try{
            boolean ifimg = regulation.VERIFY(imgFormat);
            if(!ifimg) return Result.fail("图片类型错误");

        }catch (Exception e){
            return Result.fail("请重试");
        }

        File f = new File(casePath);
        try {
            if (!f.exists()) {
                f.mkdirs();
            }
        }catch (Exception e) {
            return Result.fail("请重新上传");
        }
        String name = UUID.randomUUID().toString() + imgFormat;
        file.transferTo(new File(casePath + "\\" + name));
        String urlImg = "http://localhost:3000/img/" + dateForm + "/" + name;

        return UserServiceFactory.getUserService("TEACHER").updatePhoto(urlImg);
    }

    @PostMapping("/teacher/account/info/update/")
    public Result updateInfo(@RequestParam Map<String, String> map) {
        return UserServiceFactory.getUserService("TEACHER").updateInfo(map);
    }

    @GetMapping("/admin/get/all/teacher/")
    public Result getAllTeacher(@RequestParam Map<String, String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page < 1) {
            return Result.fail("获取老师信息失败");
        }
        return UserServiceFactory.getUserService("TEACHER").getAll(page);
    }
}
