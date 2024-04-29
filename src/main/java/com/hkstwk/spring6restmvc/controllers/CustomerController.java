package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.model.Customer;
import com.hkstwk.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        log.debug("Get list of customer, called in {}", this.getClass().getSimpleName());
        return customerService.listCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {
        log.debug("Get customer by id {}, called in {}", id, this.getClass().getSimpleName());
        return customerService.getCustomerById(id);
    }
}
