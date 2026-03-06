package com.ruishu.admin.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private String orderNo;
    private String userName;
    private String phone;
    private String productName;
    private Integer quantity;
    private BigDecimal totalAmount;
    private Integer status;
    private String address;
    private LocalDateTime createTime;
}