package com.onebit.sample.hexagonal.infra.inputport;

import java.util.List;

import com.onebit.sample.hexagonal.domain.Customer;

public interface CustomerInputPort {

    public Customer createCustomer(String name, String country);

    public Customer getById(String customerId);

    public List<Customer> getAll();
    
}
