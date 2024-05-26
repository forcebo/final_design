package com.finaldesign.backend.service.impl.student.comment;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finaldesign.backend.mapper.CommentMapper;
import com.finaldesign.backend.mapper.StudentMapper;
import com.finaldesign.backend.pojo.Comment;
import com.finaldesign.backend.pojo.CommenterInfo;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Student;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.student.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Result getCommentByReceiveId(Integer receiveId) {
        if (receiveId == null || receiveId < 0) {
            return Result.fail("参数错误");
        }
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_id", receiveId).eq("status", 1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommenterInfo> commenterInfos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int goodNum = 0;
        for (Comment comment : comments) {
            CommenterInfo commenterInfo = new CommenterInfo();
            Student student = studentMapper.selectById(comment.getSendId());
            commenterInfo.setUsername(student.getUsername());
            commenterInfo.setPhoto(student.getPhoto());
            commenterInfo.setContent(comment.getContent());
            Date time = comment.getTime();
            String formatDate = dateFormat.format(time);
            commenterInfo.setTime(formatDate);
            if (comment.getStatus() == 1) {
                goodNum++;
                commenterInfo.setIsGood("好评");
            } else {
                commenterInfo.setIsGood("坏评");
            }
            commenterInfos.add(commenterInfo);
        }
        jsonObject.put("comments", commenterInfos);
        double goodRate = (double) goodNum / (double) comments.size();
        jsonObject.put("goodRate", goodRate);
        jsonObject.put("total", comments.size());
        return Result.ok(jsonObject);
    }

    @Override
    public Result addComment(Comment comment) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配,请注册学生用户再购买课程");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();

        if (comment == null) {
            return Result.fail("评论失败");
        }
        if (comment.getReceiveId() == null || comment.getReceiveId() < 0) {
            return Result.fail("评论失败");
        }
        if (StrUtil.isBlank(comment.getContent())) {
            return Result.fail("请输入评论内容");
        }
        comment.setSendId(student.getId());
        comment.setStatus(0);
        comment.setTime(new Date());
        commentMapper.insert(comment);
        return Result.ok();
    }
}
