package com.btgpactual.task.btgpactual.service;

import com.btgpactual.task.btgpactual.dto.OrderCreatedEvent;
import com.btgpactual.task.btgpactual.dto.OrderResponse;
import com.btgpactual.task.btgpactual.entity.OrderEntity;
import com.btgpactual.task.btgpactual.entity.OrderItems;
import com.btgpactual.task.btgpactual.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private MongoTemplate mongoTemplate;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent orderCreatedEvent) {

        var entity = new OrderEntity();
        entity.setOrderId(orderCreatedEvent.codigoPedido());
        entity.setCustomerId(orderCreatedEvent.codigoCliente());
        entity.setItems(orderCreatedEvent.itens().stream().map(item ->
                new OrderItems(item.produto(), item.quantidade(), item.preco())
        ).toList());
        entity.setTotal(getTotal(orderCreatedEvent));

        orderRepository.save(entity);

    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponse::fromEntity);

    }


    private BigDecimal getTotal(OrderCreatedEvent orderCreatedEvent) {
        return orderCreatedEvent.itens().stream().map(i -> i.preco()
                        .multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
