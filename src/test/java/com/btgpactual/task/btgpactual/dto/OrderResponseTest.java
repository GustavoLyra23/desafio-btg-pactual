package com.btgpactual.task.btgpactual.dto;

import com.btgpactual.task.btgpactual.factory.OrderEntityFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderResponseTest {


    @Nested
    class fromEntity {

        @Test
        public void shouldMapCorrectly() {
            //ARRANGE
            var input = OrderEntityFactory.build();

            //ACT
            var output = OrderResponse.fromEntity(input);
            //ASSERT
            assertEquals(input.getOrderId(), output.orderId());
            assertEquals(input.getCustomerId(), output.CustomerId());
            assertEquals(input.getTotal(), output.total());


        }

    }

}
