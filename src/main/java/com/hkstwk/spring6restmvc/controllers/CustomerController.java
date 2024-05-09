package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.exceptions.NotFoundException;
import com.hkstwk.spring6restmvc.model.CustomerDTO;
import com.hkstwk.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> patchCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer) {
        if (customerService.patchCustomerById(customerId, customer).isEmpty()){
            throw new NotFoundException("Customer with id " + customerId + " not found");
        }
        log.debug("Patched customer with id {}, called in {}", customerId, this.getClass().getSimpleName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> deleteById(@PathVariable UUID customerId) {
        if (Boolean.FALSE.equals(customerService.deleteById(customerId))) {
            throw new NotFoundException("Customer with id " + customerId + " not found");
        }
        log.debug("Deleted customer with id {}, called in {}", customerId, this.getClass().getSimpleName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerDTO> updateById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer) {
        if (customerService.updateById(customerId, customer).isEmpty()){
            throw new NotFoundException("Customer with id " + customerId + " not found");
        }
        log.debug("Updated customer with id {}, called in {}", customerId, this.getClass().getSimpleName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<CustomerDTO> saveNewCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CUSTOMER_PATH.concat("/").concat(savedCustomer.getId().toString()));
        log.debug("Save new customer with id {}, called in {}", savedCustomer.getId(), this.getClass().getSimpleName());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listCustomers() {
        log.debug("Get list of customer, called in {}", this.getClass().getSimpleName());
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id) {
        log.debug("Get customer by id {}, called in {}", id, this.getClass().getSimpleName());
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }
}
