package com.finaldesign.backend.service.impl.student.comment;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finaldesign.backend.mapper.CommentMapper;
import com.finaldesign.backend.mapper.StudentMapper;
import com.finaldesign.backend.mapper.TeacherMapper;
import com.finaldesign.backend.pojo.*;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.impl.utils.TeacherDetailsImpl;
import com.finaldesign.backend.service.student.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Result getCommentByReceiveId(Integer receiveId, Integer page) {
        if (receiveId == null || receiveId < 0) {
            return Result.fail("参数错误");
        }
        IPage<Comment> recordPage = new Page<>(page, 10);
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_id", receiveId).eq("status", 1)
                .orderByDesc("id").eq("receive_identity", 1);
        List<Comment> comments = commentMapper.selectPage(recordPage,queryWrapper).getRecords();
        List<CommenterInfo> commenterInfos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int goodNum = 0;
        for (Comment comment : comments) {
            CommenterInfo commenterInfo = new CommenterInfo();
            Student student = studentMapper.selectById(comment.getSendId());
            commenterInfo.setId(comment.getId());
            commenterInfo.setStudentId(student.getId());
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
        jsonObject.put("records", commenterInfos);
        if (!comments.isEmpty()) {
            double goodRate = (double) goodNum / (double) comments.size();
            jsonObject.put("goodRate", goodRate);
        }
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("receive_id", receiveId).eq("status",1)
                .eq("is_examine", 1).eq("receive_identity", 1);
        jsonObject.put("total_records", commentMapper.selectCount(commentQueryWrapper));
        return Result.ok(jsonObject);
    }

    @Override
    public Result addComment(Comment comment) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配,请注册学生用户评论");
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
        comment.setIsExamine(0);
        comment.setTime(new Date());
        comment.setReceiveIdentity(1);
        commentMapper.insert(comment);
        return Result.ok();
    }

    @Override
    public Result getAllComments(Integer page) {
        IPage<Comment> recordPage = new Page<>(page, 10);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Comment> records = commentMapper.selectPage(recordPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        List<JSONObject> items = new LinkedList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Comment comment : records) {
            JSONObject item = new JSONObject();
            item.put("id", comment.getId());
            item.put("content", comment.getContent());
            item.put("time", sdf.format(comment.getTime()));
            item.put("isGood", comment.getIsGood());
            item.put("status", comment.getStatus());
            item.put("isExamine", comment.getIsExamine());
            items.add(item);
        }
        resp.put("records", items);
        resp.put("total_records", commentMapper.selectCount(null));
        return Result.ok(resp);
    }

    @Override
    public Result examineYesById(Integer id) {
        Comment comment = commentMapper.selectById(id);
        if (comment != null) {
            comment.setIsExamine(1);
            comment.setStatus(1);
            commentMapper.updateById(comment);
        }
        return Result.ok();
    }

    @Override
    public Result examineNotById(Integer id) {
        Comment comment = commentMapper.selectById(id);
        if (comment != null) {
            comment.setIsExamine(1);
            comment.setStatus(0);
            commentMapper.updateById(comment);
        }
        return Result.ok();
    }

    @Override
    public Result getCommentsByStudentId(Integer page) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配,请登录教师用户");
        }
        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        IPage<Comment> recordPage = new Page<>(page, 10);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_id", student.getId()).orderByDesc("id");
        List<Comment> records = commentMapper.selectPage(recordPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("records", records);
        resp.put("total_records", records.size());
        return Result.ok(resp);
    }

    @Override
    public Result replyComment(Comment comment) {
        Integer sendId = null;
        Integer receiveIdentity = null;
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken.getPrincipal() instanceof TeacherDetailsImpl) {
            sendId = ((TeacherDetailsImpl) authenticationToken.getPrincipal()).getTeacher().getId();
            receiveIdentity = 0;
        } else if (authenticationToken.getPrincipal() instanceof StudentDetailsImpl) {
            sendId = ((StudentDetailsImpl) authenticationToken.getPrincipal()).getStudent().getId();
            receiveIdentity = 1;
        }

        if (comment == null) {
            return Result.fail("回复失败");
        }
        if (comment.getReceiveId() == null || comment.getReceiveId() < 0) {
            return Result.fail("回复失败");
        }
        if (StrUtil.isBlank(comment.getContent())) {
            return Result.fail("请输入评论内容");
        }
        comment.setSendId(sendId);
        comment.setStatus(0);
        comment.setIsExamine(0);
        comment.setReceiveIdentity(receiveIdentity);
        comment.setTime(new Date());
        commentMapper.insert(comment);
        return Result.ok();
    }

    @Override
    public Result getCommentsByIdentity(Integer receiveId, Integer page) {
        Integer receiveIdentity = 0;
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken.getPrincipal() instanceof TeacherDetailsImpl) {
            receiveIdentity = 1;
        }
        if (receiveId == null || receiveId < 0) {
            return Result.fail("参数错误");
        }
        IPage<Comment> recordPage = new Page<>(page, 10);
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_id", receiveId).eq("status", 1).eq("is_examine", 1)
                .orderByDesc("id").eq("receive_identity", receiveIdentity);
        List<Comment> comments = commentMapper.selectPage(recordPage,queryWrapper).getRecords();
        List<CommenterInfo> commenterInfos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int goodNum = 0;
        for (Comment comment : comments) {
            CommenterInfo commenterInfo = new CommenterInfo();
            if (receiveIdentity == 1) {
                Student student = studentMapper.selectById(comment.getSendId());
                commenterInfo.setId(comment.getId());
                commenterInfo.setStudentId(student.getId());
                commenterInfo.setUsername(student.getUsername());
                commenterInfo.setPhoto(student.getPhoto());
                commenterInfo.setContent(comment.getContent());
                Date time = comment.getTime();
                String formatDate = dateFormat.format(time);
                commenterInfo.setTime(formatDate);
            } else {
                Teacher teacher = teacherMapper.selectById(comment.getSendId());
                commenterInfo.setId(comment.getId());
                commenterInfo.setStudentId(teacher.getId());
                commenterInfo.setUsername(teacher.getUsername());
                commenterInfo.setPhoto(teacher.getPhoto());
                commenterInfo.setContent(comment.getContent());
                Date time = comment.getTime();
                String formatDate = dateFormat.format(time);
                commenterInfo.setTime(formatDate);
            }
            commenterInfos.add(commenterInfo);
        }
        jsonObject.put("records", commenterInfos);
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();

        commentQueryWrapper.eq("receive_id", receiveId).eq("status",1)
                .eq("is_examine", 1).eq("receive_identity", receiveIdentity);
        jsonObject.put("total_records", commentMapper.selectCount(commentQueryWrapper));
        return Result.ok(jsonObject);
    }

    @Override
    public Result getCommentsByTeacherId(Integer page) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof TeacherDetailsImpl)) {
            return Result.fail("token不匹配,请登录教师用户");
        }
        TeacherDetailsImpl loginUser = (TeacherDetailsImpl) authenticationToken.getPrincipal();
        Teacher teacher = loginUser.getTeacher();
        IPage<Comment> recordPage = new Page<>(page, 10);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("receive_id", teacher.getId()).orderByDesc("id");
        List<Comment> records = commentMapper.selectPage(recordPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("records", records);
        resp.put("total_records", records.size());
        return Result.ok(resp);
    }
}
