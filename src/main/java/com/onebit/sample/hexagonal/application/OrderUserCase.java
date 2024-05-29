package com.onebit.sample.hexagonal.application;

import java.math.BigDecimal;
import java.util.UUID;

import com.onebit.sample.hexagonal.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.onebit.sample.hexagonal.infra.inputport.OrderInputPort;
import com.onebit.sample.hexagonal.infra.outputport.EntityRepository;

@Component
@RequiredArgsConstructor
public class OrderUserCase implements OrderInputPort {

    final EntityRepository entityRepository;

    @Override
    public Orders createOrder(String customerId, BigDecimal total) {
        Orders order = Orders.builder()
            .id( UUID.randomUUID().toString() )
            .customerId( customerId )
            .total( total )
            .build();

        return entityRepository.save( order );
    }
    
}
