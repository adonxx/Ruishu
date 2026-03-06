package com.ruishu.admin.controller;

import com.ruishu.admin.common.Result;
import com.ruishu.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public Result list() {
        return orderService.list();
    }

    @PutMapping("/status/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,
                               @PathVariable Integer status) {
        return orderService.updateStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return orderService.delete(id);
    }
}