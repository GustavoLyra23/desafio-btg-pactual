package com.btgpactual.task.btgpactual.repository;

import com.btgpactual.task.btgpactual.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
