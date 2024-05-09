package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<CustomerDTO> getCustomerById(UUID id);
    List<CustomerDTO> listCustomers();
    CustomerDTO saveNewCustomer(CustomerDTO customer);
    Optional<CustomerDTO> updateById(UUID customerId, CustomerDTO customer);
    Boolean deleteById(UUID customerId);
    Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer);
}
