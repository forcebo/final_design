package com.finaldesign.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_teacher")
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String identity; // 身份
    private String realname;
    private String sex;
    private Integer age;
    private String education; // 学历
    private String major; // 专业
    private String phone;
    private String address;
    private String role;
    private String photo;
    private String description;
    private String city;

    public Teacher(Integer id, String username, String password, String identity, String realname, String sex, Integer age, String education, String major, String phone, String address, String photo, String city) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.identity = identity;
        this.realname = realname;
        this.sex = sex;
        this.age = age;
        this.education = education;
        this.major = major;
        this.phone = phone;
        this.address = address;
        this.role = "TEACHER";
        this.photo = photo;
        this.city = city;
    }
}
