package com.finaldesign.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommenterInfo {
    String username;
    String photo;
    String content;
    String time;
    String isGood;
}
