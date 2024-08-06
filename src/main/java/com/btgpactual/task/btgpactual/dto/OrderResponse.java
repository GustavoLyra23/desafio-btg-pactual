package com.btgpactual.task.btgpactual.dto;

import com.btgpactual.task.btgpactual.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderId, Long CustomerId, BigDecimal total) {


    public static OrderResponse fromEntity(OrderEntity order) {
        return new OrderResponse(order.getOrderId(), order.getCustomerId(), order.getTotal());
    }


}
