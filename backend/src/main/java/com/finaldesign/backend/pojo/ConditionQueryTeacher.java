package com.finaldesign.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionQueryTeacher {
    private Integer id;
    private String subject;
    private String area;
    private String school;
    private String education;
    private String sex;
    private String mode;
}
