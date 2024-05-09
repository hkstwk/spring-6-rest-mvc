package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.entities.Customer;
import com.hkstwk.spring6restmvc.exceptions.NotFoundException;
import com.hkstwk.spring6restmvc.mappers.CustomerMapper;
import com.hkstwk.spring6restmvc.model.CustomerDTO;
import com.hkstwk.spring6restmvc.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private BeerController beerController;

    @Test
    void testListCustomers() {
        List<CustomerDTO> customerDTOList = customerController.listCustomers();
        assertThat(customerDTOList).hasSize(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyListCustomers() {
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

    @Rollback
    @Transactional
    @Test
    void testSaveNewCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("My brand new customer")
                .build();

        ResponseEntity<CustomerDTO> responseEntity = customerController.saveNewCustomer(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] LocationSegments = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID customerId = UUID.fromString(LocationSegments[LocationSegments.length - 1]);
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        assertThat(customer).isNotNull();
    }

    @Test
    void testUpdateCustomerNotFound() {
        UUID uuid = UUID.randomUUID();
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        assertThrows(NotFoundException.class, () -> customerController.updateById(uuid, customerDTO));
    }

    @Rollback
    @Transactional
    @Test
    void testUpdateCustomerFound() {
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String updatedCustomerName = "My updated integration test customer name";
        customerDTO.setCustomerName(updatedCustomerName);

        ResponseEntity<CustomerDTO> responseEntity = customerController.updateById(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElseThrow();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(updatedCustomerName);
    }

    @Test
    void testDeleteCustomerNotFound() {
        UUID uuid = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> customerController.deleteById(uuid));
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteCustomer() {
        Customer customer = customerRepository.findAll().getFirst();

        ResponseEntity<CustomerDTO> responseEntity = customerController.deleteById(customer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }
}