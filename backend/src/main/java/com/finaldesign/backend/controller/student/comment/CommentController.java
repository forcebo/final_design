package com.finaldesign.backend.controller.student.comment;

import com.finaldesign.backend.pojo.Comment;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/get/{receiveId}/{page}/")
    public Result getCommentByReceive(@PathVariable Integer receiveId, @PathVariable Integer page) {
        return commentService.getCommentByReceiveId(receiveId, page);
    }

    @PostMapping("/comment/add/")
    public Result addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/admin/get/all/comment/")
    public Result getAllComments(@RequestParam Map<String,String> data){
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page < 1) {
            return Result.fail("获取求职信息失败");
        }
        return commentService.getAllComments(page);
    }

    @PostMapping("/admin/examine/comment/yes/{id}/")
    public Result examineYesById(@PathVariable Integer id) {
        return commentService.examineYesById(id);
    }

    @PostMapping("/admin/examine/comment/not/{id}/")
    public Result examineNotById(@PathVariable Integer id) {
        return commentService.examineNotById(id);
    }

    @GetMapping("/student/get/comments/")
    public Result getCommentsByStudentId(@RequestParam Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page < 1) {
            return Result.fail("获取求职信息失败");
        }
        return commentService.getCommentsByStudentId(page);
    }

    @GetMapping("/teacher/get/comments/")
    public Result getCommentsByTeacherId(@RequestParam Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page < 1) {
            return Result.fail("获取求职信息失败");
        }
        return commentService.getCommentsByTeacherId(page);
    }


}
