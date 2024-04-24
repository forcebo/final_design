package com.finaldesign.backend.service.user.account;

import com.finaldesign.backend.pojo.Result;

public interface StudentInfoService {
    Result updateStudentInfo(String username, String photo, String realname, String sex, String phone, String address);
    Result updateStudentPassword(String password, String changePassword, String changeConfirmPassword);
    Result updateStudentPhoto(String photo);
}
