package com.finaldesign.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finaldesign.backend.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
