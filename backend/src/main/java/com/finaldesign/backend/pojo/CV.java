package com.finaldesign.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简历
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_CV")
public class CV {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String subjects;
    private String certificate;
    private String description;
    private String areas;
    private String studentEvaluate;
    private String mode;
    private String salary;
    private Integer teacherId;
    private Integer status;
    private Integer isExamine;
}
