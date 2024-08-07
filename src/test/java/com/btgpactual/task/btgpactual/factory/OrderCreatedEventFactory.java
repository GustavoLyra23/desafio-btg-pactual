package com.btgpactual.task.btgpactual.factory;

import com.btgpactual.task.btgpactual.dto.OrderCreatedEvent;
import com.btgpactual.task.btgpactual.dto.OrderItemEvent;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreatedEventFactory {

    public static OrderCreatedEvent buildWithOneItem() {
        List<OrderItemEvent> itens = List.of(new OrderItemEvent("notebook", 1, BigDecimal.valueOf(20.50)));
        return new OrderCreatedEvent(1L, 2L, itens);
    }

    public static OrderCreatedEvent buildWithTwoItems() {
        var item1 = new OrderItemEvent("notebook", 1, BigDecimal.valueOf(20.50));
        var item2 = new OrderItemEvent("mouse", 1, BigDecimal.valueOf(35.25));
        return new OrderCreatedEvent(1L, 2L, List.of(item1, item2));
    }

}
