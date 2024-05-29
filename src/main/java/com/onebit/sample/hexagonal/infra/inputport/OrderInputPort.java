package com.onebit.sample.hexagonal.infra.inputport;

import java.math.BigDecimal;

import com.onebit.sample.hexagonal.domain.Orders;

public interface OrderInputPort {
    public Orders createOrder(String customerId, BigDecimal total );
}
