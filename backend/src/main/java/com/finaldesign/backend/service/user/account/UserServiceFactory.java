package com.finaldesign.backend.service.user.account;

import com.finaldesign.backend.service.impl.user.account.AdminServiceImpl;
import com.finaldesign.backend.service.impl.user.account.StudentServiceImpl;
import com.finaldesign.backend.service.impl.user.account.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserServiceFactory {
    private final static HashMap<String, UserService> USER_SERVICE_CACHE = new HashMap<>(8);


    @Autowired
    public UserServiceFactory(StudentServiceImpl studentService, TeacherServiceImpl teacherService, AdminServiceImpl adminService) {
        USER_SERVICE_CACHE.put("student", studentService);
        USER_SERVICE_CACHE.put("teacher", teacherService);
        USER_SERVICE_CACHE.put("admin", adminService);
    }

    public static UserService getUserService(String role) {
        return USER_SERVICE_CACHE.get(role.toLowerCase());
    }
}
