package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(UUID id);
    List<Customer> listCustomers();

    Customer saveNewCustomer(Customer customer);
}
