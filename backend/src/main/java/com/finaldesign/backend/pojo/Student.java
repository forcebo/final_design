package com.finaldesign.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_student")
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String photo;
    private String realname;
    private String sex;
    private String phone;
    private String address;
    private String role;

    public Student(Integer id, String username, String password, String photo, String realname, String sex, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.realname = realname;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.role = "STUDENT";
    }
}
