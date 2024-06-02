package com.finaldesign.backend.service.student.comment;

import com.finaldesign.backend.pojo.Comment;
import com.finaldesign.backend.pojo.Result;

public interface CommentService {
    Result getCommentByReceiveId(Integer receiveId, Integer page);
    Result addComment(Comment comment);
    Result getAllComments(Integer page);
    Result examineYesById(Integer id);
    Result examineNotById(Integer id);
    Result getCommentsByTeacherId(Integer page);
    Result getCommentsByStudentId(Integer page);
}
