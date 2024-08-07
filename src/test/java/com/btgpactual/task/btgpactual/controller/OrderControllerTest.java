package com.btgpactual.task.btgpactual.controller;

import com.btgpactual.task.btgpactual.dto.OrderResponse;
import com.btgpactual.task.btgpactual.factory.OrderResponseFactory;
import com.btgpactual.task.btgpactual.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Captor
    ArgumentCaptor<Long> customerIdCaptor;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestCaptor;

    private long customerId;
    private int page;
    private int pageSize;
    private Page<OrderResponse> pagination;

    @BeforeEach
    void setUp() {
        //ARRANGE
        customerId = 1L;
        page = 0;
        pageSize = 10;
        pagination = OrderResponseFactory.buildWithOneItem();
    }

    @Nested
    class listOrders {

        @Test
        void ShouldReturnHttpOk() {

            doReturn(OrderResponseFactory.buildWithOneItem()).when(orderService).findAllByCustomerId(anyLong(), any());
            //ACT
            var response = orderController.listOrders(customerId, page, pageSize);
            //ASSERTS
            assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        }

        @Test
        void ShouldPassCorrectParametersToService() {
            doReturn(OrderResponseFactory.buildWithOneItem()).when(orderService)
                    .findAllByCustomerId(customerIdCaptor.capture(), pageRequestCaptor.capture());
            //ACT
            orderController.listOrders(customerId, page, pageSize);
            //ASSERTS
            assertEquals(customerId, customerIdCaptor.getValue());
            assertEquals(1, customerIdCaptor.getAllValues().size());
            assertEquals(pageRequestCaptor.getValue().getPageNumber(), page);
            assertEquals(pageRequestCaptor.getValue().getPageSize(), pageSize);
        }

        @Test
        void ShouldReturnResponseBodyCorrectly() {
            doReturn(pagination).when(orderService)
                    .findAllByCustomerId(anyLong(), any());
            //ACT
            var response = orderController.listOrders(customerId, page, pageSize);
            //ASSERTS
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertNotNull(response.getBody().data());
            assertNotNull(response.getBody().paginationResponse());
            assertNotNull(response.getBody().paginationResponse().totalElements());

            assertEquals(pagination.getTotalElements(), response.getBody().paginationResponse().totalElements());
            assertEquals(pagination.getTotalPages(), response.getBody().paginationResponse().totalPages());
            assertEquals(pagination.getNumber(), response.getBody().paginationResponse().page());
            assertEquals(pagination.getSize(), response.getBody().paginationResponse().pageSize());
            assertEquals(pagination.getContent(), response.getBody().data());
        }


    }


}
