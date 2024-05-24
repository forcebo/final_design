package com.finaldesign.backend.controller.student.order;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.Order;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/student/course/order/add/")
    private Result releaseJobInformation(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping("/student/course/order/check/{id}/")
    private Result checkOrder(@PathVariable Integer id) {
        return orderService.checkOrder(id);
    }
}
