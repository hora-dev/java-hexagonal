package com.onebit.sample.hexagonal.infra.inputadapter.http;


import java.util.List;

import com.onebit.sample.hexagonal.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onebit.sample.hexagonal.infra.inputport.CustomerInputPort;

@RestController
@RequestMapping(value = "customer")
@RequiredArgsConstructor
public class CustomerAPI {

    final CustomerInputPort customerInputPort;

    @PostMapping(value = "create", produces=MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestParam String name, @RequestParam String country ) {
        return customerInputPort.createCustomer(name, country);
        }

    @PostMapping(value = "get", produces=MediaType.APPLICATION_JSON_VALUE)
    public Customer get( @RequestParam String customerId ) {
        return customerInputPort.getById(customerId);
        }

    @PostMapping(value = "getall", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAll() {
        return customerInputPort.getAll();
        }
           
}
