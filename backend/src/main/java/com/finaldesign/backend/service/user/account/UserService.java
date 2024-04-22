package com.finaldesign.backend.service.user.account;

import com.finaldesign.backend.pojo.Result;

import java.util.Map;

public interface UserService {
    Result getInfo();
    Result getToken(String username, String password);
    Result register(Map<String, String> map);
}
