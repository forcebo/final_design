package com.finaldesign.backend.service.impl.student.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finaldesign.backend.mapper.OrderMapper;
import com.finaldesign.backend.pojo.Order;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.Student;
import com.finaldesign.backend.service.impl.utils.StudentDetailsImpl;
import com.finaldesign.backend.service.student.order.OrderService;
import com.finaldesign.backend.utils.OrderIdCreateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
}
