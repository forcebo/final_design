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
@TableName("t_reserve")
public class Reserve {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private Integer teacherId;
    private String name;
    private String phone;
    private String area;
    private String areaDetail;
    private String studentSex;
    private String studentGrade;
    private String subject;
    private String classTime;
    private String studentDetail;
    private String mode;
    private String teacherRequire;
    private String charge;
}
