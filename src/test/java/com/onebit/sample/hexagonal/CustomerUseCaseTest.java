package com.onebit.sample.hexagonal;

import com.onebit.sample.hexagonal.application.CustomerUseCase;
import com.onebit.sample.hexagonal.domain.Customer;
import com.onebit.sample.hexagonal.infra.outputport.EntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerUseCaseTest {
    @Mock
    private EntityRepository entityRepository;

    @InjectMocks
    private CustomerUseCase customerUseCase;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(UUID.randomUUID().toString())
                .name("John Doe")
                .country("USA")
                .build();
    }

    @Test
    void testCreateCustomer() {
        when(entityRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerUseCase.createCustomer("John Doe", "USA");

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("USA", result.getCountry());
        verify(entityRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testGetById() {
        String customerId = customer.getId();
        when(entityRepository.getById(customerId, Customer.class)).thenReturn(customer);

        Customer result = customerUseCase.getById(customerId);

        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals("John Doe", result.getName());
        verify(entityRepository, times(1)).getById(customerId, Customer.class);
    }

    @Test
    void testGetAll() {
        List<Customer> customers = Arrays.asList(customer);
        when(entityRepository.getAll(Customer.class)).thenReturn(customers);

        List<Customer> result = customerUseCase.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(entityRepository, times(1)).getAll(Customer.class);
    }
}