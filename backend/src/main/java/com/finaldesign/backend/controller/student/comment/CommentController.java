package com.finaldesign.backend.controller.student.comment;

import com.finaldesign.backend.pojo.Comment;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/get/{receiveId}/")
    public Result getCommentByReceive(@PathVariable Integer receiveId) {
        return commentService.getCommentByReceiveId(receiveId);
    }

    @PostMapping("/comment/add/")
    public Result addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }
}
