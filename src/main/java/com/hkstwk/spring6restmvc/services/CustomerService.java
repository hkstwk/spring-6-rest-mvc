package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<Customer> getCustomerById(UUID id);
    List<Customer> listCustomers();
    Customer saveNewCustomer(Customer customer);
    void updateById(UUID customerId, Customer customer);
    void deleteById(UUID customerId);
    void patchCustomerById(UUID customerId, Customer customer);
}
