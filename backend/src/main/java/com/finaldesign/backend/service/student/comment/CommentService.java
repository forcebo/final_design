package com.finaldesign.backend.service.student.comment;

import com.finaldesign.backend.pojo.Result;

public interface CommentService {
    Result getCommentByReceiveId(Integer receiveId);
}
