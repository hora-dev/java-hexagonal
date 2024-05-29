package com.onebit.sample.hexagonal;

import com.onebit.sample.hexagonal.application.OrderUserCase;
import com.onebit.sample.hexagonal.domain.Orders;
import com.onebit.sample.hexagonal.infra.outputport.EntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderUserCaseTest {

    @Mock
    private EntityRepository entityRepository;

    @InjectMocks
    private OrderUserCase orderUserCase;

    private Orders order;

    @BeforeEach
    void setUp() {
        order = Orders.builder()
                .id(UUID.randomUUID().toString())
                .customerId("customer-123")
                .total(new BigDecimal("100.00"))
                .build();
    }

    @Test
    void testCreateOrder() {
        when(entityRepository.save(any(Orders.class))).thenReturn(order);

        Orders result = orderUserCase.createOrder("customer-123", new BigDecimal("100.00"));

        assertNotNull(result);
        Assertions.assertEquals("customer-123", result.getCustomerId());
        Assertions.assertEquals(new BigDecimal("100.00"), result.getTotal());
        verify(entityRepository, times(1)).save(any(Orders.class));
    }
}