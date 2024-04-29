package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.model.Customer;
import com.hkstwk.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateById(@PathVariable("customerId") UUID customerId,  @RequestBody Customer customer) {
        customerService.updateById(customerId, customer);
        log.debug("Updated customer with id {}, called in {}", customerId, this.getClass().getSimpleName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Customer> handlePost(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/" + savedCustomer.getId());
        log.debug("Save new customer with id {}, called in {}", savedCustomer.getId(), this.getClass().getSimpleName());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

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
