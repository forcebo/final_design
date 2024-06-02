package com.finaldesign.backend.service.impl.student.order;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finaldesign.backend.mapper.OrderMapper;
import com.finaldesign.backend.pojo.*;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.student.order.OrderService;
import com.finaldesign.backend.utils.OrderIdCreateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Result addOrder(Order order) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配,请注册学生用户再购买课程");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        if (order == null) {
            return Result.fail("订单创建失败");
        }
        if (order.getTeacherId() == null || order.getTeacherId() <= 0) {
            return Result.fail("订单创建失败");
        }
        if (order.getPrice() == null) {
            return Result.fail("订单创建失败");
        }
        if (order.getCourseId() == null || order.getCourseId() <= 0) {
            return Result.fail("订单创建失败");
        }
        order.setStudentId(student.getId());
        order.setStatus(0);
        order.setTime(new Date());
        order.setNo(OrderIdCreateUtil.nextId());
        orderMapper.insert(order);

        return Result.ok();
    }

    @Override
    public Result checkOrder(Integer courseId) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配,请注册学生用户再购买课程");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();

        if (courseId == null || courseId <= 0) {
            return Result.fail("获取订单信息失败");
        }
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        for (Order order : orders) {
            if (order.getStudentId() == student.getId()) {
                return Result.ok(true);
            }
        }
        return Result.ok(false);
    }

    @Override
    public Result getOrdersByStudentId() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authenticationToken == null || !(authenticationToken.getPrincipal() instanceof StudentDetailsImpl)) {
            return Result.fail("token不匹配,请注册学生用户再购买课程");
        }

        StudentDetailsImpl loginUser = (StudentDetailsImpl) authenticationToken.getPrincipal();
        Student student = loginUser.getStudent();
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", student.getId());

        List<Order> orders = orderMapper.selectList(queryWrapper);
        return Result.ok(orders);
    }

    @Override
    public Result getAllOrders(Integer page) {
        IPage<Order> recordPage = new Page<>(page, 10);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Order> records = orderMapper.selectPage(recordPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        List<JSONObject> items = new LinkedList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Order order : records) {
            JSONObject item = new JSONObject();
            item.put("id", order.getId());
            item.put("no", order.getNo());
            item.put("studentId",order.getStudentId());
            item.put("teacherId",order.getTeacherId());
            item.put("courseId",order.getCourseId());
            item.put("price",order.getPrice());
            item.put("time",sdf.format(order.getTime()));
            item.put("status", order.getStatus());
            item.put("isExamine", order.getIsExamine());
            items.add(item);
        }
        resp.put("records", items);
        resp.put("total_records", orderMapper.selectCount(null));
        return Result.ok(resp);
    }

    @Override
    public Result examineYesById(Integer id) {
        Order order = orderMapper.selectById(id);
        if (order != null) {
            order.setIsExamine(1);
            order.setStatus(1);
            orderMapper.updateById(order);
        }
        return Result.ok();
    }

    @Override
    public Result examineNotById(Integer id) {
        Order order = orderMapper.selectById(id);
        if (order != null) {
            order.setIsExamine(1);
            order.setStatus(0);
            orderMapper.updateById(order);
        }
        return Result.ok();
    }
}
