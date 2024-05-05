package com.hkstwk.spring6restmvc.bootstrap;

import com.hkstwk.spring6restmvc.repositories.BeerRepository;
import com.hkstwk.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BootStrapDataTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootStrapData bootStrapData;

    @BeforeEach
    void setUp() {
        bootStrapData = new BootStrapData(beerRepository, customerRepository);
    }

    @Test
    void testData() throws Exception {
        bootStrapData.run();

        assertThat(beerRepository.count()).isEqualTo(4);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}