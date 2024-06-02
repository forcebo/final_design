package com.finaldesign.backend.controller.student.order;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.Order;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/student/course/order/add/")
    public Result releaseJobInformation(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping("/student/course/order/check/{id}/")
    public Result checkOrder(@PathVariable Integer id) {
        return orderService.checkOrder(id);
    }

    @GetMapping("/student/course/order/get/")
    public Result getOrdersByStudentId() {
        return orderService.getOrdersByStudentId();
    }

    @GetMapping("/admin/get/all/order/")
    public Result getAllOrders(@RequestParam Map<String,String> data){
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page < 1) {
            return Result.fail("获取求职信息失败");
        }
        return orderService.getAllOrders(page);
    }

    @PostMapping("/admin/examine/order/yes/{id}/")
    public Result examineYesById(@PathVariable Integer id) {
        return orderService.examineYesById(id);
    }

    @PostMapping("/admin/examine/order/not/{id}/")
    public Result examineNotById(@PathVariable Integer id) {
        return orderService.examineNotById(id);
    }
}
