package com.hkstwk.spring6restmvc.mappers;

import com.hkstwk.spring6restmvc.entities.Customer;
import com.hkstwk.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
