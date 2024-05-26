package com.finaldesign.backend.service.student.order;

import com.finaldesign.backend.pojo.Order;
import com.finaldesign.backend.pojo.Result;

public interface OrderService {
    Result addOrder(Order order);
    Result checkOrder(Integer courseId);
    Result getOrdersByStudentId();
}
