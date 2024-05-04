package com.finaldesign.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("t_student_requirement")
public class Requirement {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String studentName;
    private String phone;
    private String area;
    private String areaDetailDescription;
    private String sex;
    private String grade;
    private String subject;
    private String classtime;
    @TableField("teacher_condition")
    private String condition;
    private String teacherSex;
    private String mode;
    private String requirement;
    private String charge;
    private Integer studentId;
}
