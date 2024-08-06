package com.btgpactual.task.btgpactual.controller;

import com.btgpactual.task.btgpactual.dto.ApiResponse;
import com.btgpactual.task.btgpactual.dto.OrderResponse;
import com.btgpactual.task.btgpactual.dto.PaginationResponse;
import com.btgpactual.task.btgpactual.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@PathVariable("customerId") Long customerId,
                                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "pageSize", defaultValue = "1")
                                                                 Integer pageSize) {

        var body = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new ApiResponse<>(body.getContent(), PaginationResponse.fromPage(body)));
    }


}
