package com.finaldesign.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Top5ReserveTeacherInfo {
    private Integer id;
    private String realName;
    private Long reserveCount;
}
