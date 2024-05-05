package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.entities.Customer;
import com.hkstwk.spring6restmvc.exceptions.NotFoundException;
import com.hkstwk.spring6restmvc.model.CustomerDTO;
import com.hkstwk.spring6restmvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testListCustomers() {
        List<CustomerDTO> customerDTOList = customerController.listCustomers();
        assertThat(customerDTOList).hasSize(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyListCutomers() {
        customerRepository.deleteAll();
        List<CustomerDTO> customerDTOList = customerController.listCustomers();
        assertThat(customerDTOList).isEmpty();
    }

    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());
        assertThat(customerDTO).isNotNull();
    }

    @Test
    void testGetCustomerByIdNotFound() {
        UUID uuid = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(uuid));
    }
}