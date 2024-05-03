package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<CustomerDTO> getCustomerById(UUID id);
    List<CustomerDTO> listCustomers();
    CustomerDTO saveNewCustomer(CustomerDTO customer);
    void updateById(UUID customerId, CustomerDTO customer);
    void deleteById(UUID customerId);
    void patchCustomerById(UUID customerId, CustomerDTO customer);
}
