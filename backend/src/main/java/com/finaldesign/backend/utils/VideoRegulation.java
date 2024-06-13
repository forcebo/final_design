package com.finaldesign.backend.utils;

public class VideoRegulation {
    public boolean verify(String fileExtension) {
        // 可以随意添加视频类型 无需改代码
        String[] videoFormats = {".mp4", ".avi", ".mov", ".mkv", ".flv", ".wmv", ".webm", ".mpeg"};
        for (String videoFormat : videoFormats) {
            if (videoFormat.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }
}
