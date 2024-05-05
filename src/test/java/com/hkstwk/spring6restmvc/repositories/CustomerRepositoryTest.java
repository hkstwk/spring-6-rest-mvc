package com.hkstwk.spring6restmvc.repositories;

import com.hkstwk.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void saveCustomer(){
        Customer savedCustomer = customerRepository.save(Customer.builder()
                        .customerName("My new customer")
                .build());

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getCustomerName()).isEqualTo("My new customer");
        assertThat(savedCustomer.getId()).isNotNull();
    }

}