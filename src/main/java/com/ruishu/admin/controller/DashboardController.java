package com.ruishu.admin.controller;

import com.ruishu.admin.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/overview")
    public Result overview() {
        // 总销售额
        Double totalAmount = jdbcTemplate.queryForObject(
            "SELECT COALESCE(SUM(total_amount),0) FROM orders WHERE status != 4",
            Double.class);
        // 总订单数
        Integer totalOrders = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM orders",
            Integer.class);
        // 商品总数
        Integer totalProducts = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM product",
            Integer.class);
        // 待处理订单（待付款+待发货）
        Integer pendingOrders = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM orders WHERE status IN (0,1)",
            Integer.class);

        Map<String, Object> data = new HashMap<>();
        data.put("totalAmount", totalAmount);
        data.put("totalOrders", totalOrders);
        data.put("totalProducts", totalProducts);
        data.put("pendingOrders", pendingOrders);
        return Result.ok(data);
    }

    @GetMapping("/order-status")
    public Result orderStatus() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(
            "SELECT status, COUNT(*) as count FROM orders GROUP BY status"
        );
        return Result.ok(list);
    }

    @GetMapping("/product-category")
    public Result productCategory() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(
            "SELECT category, COUNT(*) as count FROM product GROUP BY category"
        );
        return Result.ok(list);
    }
}