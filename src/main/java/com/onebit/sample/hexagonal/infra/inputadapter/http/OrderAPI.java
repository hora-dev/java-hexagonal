package com.onebit.sample.hexagonal.infra.inputadapter.http;

import com.onebit.sample.hexagonal.domain.Orders;
import com.onebit.sample.hexagonal.infra.inputport.OrderInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "order")
@RequiredArgsConstructor
public class OrderAPI {
    
    final OrderInputPort orderInputPort;

    @PostMapping(value = "create", produces=MediaType.APPLICATION_JSON_VALUE)
    public Orders create(@RequestParam String customerId, @RequestParam BigDecimal total ) {
        return orderInputPort.createOrder(customerId, total);
        }
    
}
