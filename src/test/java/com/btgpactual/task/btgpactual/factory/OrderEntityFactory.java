package com.btgpactual.task.btgpactual.factory;

import com.btgpactual.task.btgpactual.entity.OrderEntity;
import com.btgpactual.task.btgpactual.entity.OrderItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.List;

public class OrderEntityFactory {




    public static OrderEntity build() {
        var items = new OrderItems("Notebook", 1, BigDecimal.valueOf(25.50));

        var entity = new OrderEntity();
        entity.setOrderId(1L);
        entity.setCustomerId(2L);
        entity.setTotal(BigDecimal.valueOf(25.50));
        entity.setItems(List.of(items));
        return entity;
    }

    public static Page<OrderEntity> buildPage() {
       return new PageImpl<>(List.of(build()));
    }




}
