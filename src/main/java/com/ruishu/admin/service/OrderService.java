package com.ruishu.admin.service;

import com.ruishu.admin.common.Result;
import com.ruishu.admin.entity.Order;
import com.ruishu.admin.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Result list() {
        List<Order> list = orderMapper.listAll();
        return Result.ok(list);
    }

    public Result updateStatus(Long id, Integer status) {
        orderMapper.updateStatus(id, status);
        return Result.ok("状态更新成功");
    }

    public Result delete(Long id) {
        orderMapper.delete(id);
        return Result.ok("删除成功");
    }
}