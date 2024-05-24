package com.finaldesign.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommenterInfo {
    private String username;
    private String photo;
    private String content;
    private String time;
    private String isGood;
}
