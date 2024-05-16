package com.hkstwk.spring6restmvc.bootstrap;

import com.hkstwk.spring6restmvc.repositories.BeerRepository;
import com.hkstwk.spring6restmvc.repositories.CustomerRepository;
import com.hkstwk.spring6restmvc.services.BeerCsvService;
import com.hkstwk.spring6restmvc.services.BeerCsvServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BeerCsvServiceImpl.class)
class BootStrapDataTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerCsvService beerCsvService;

    BootStrapData bootStrapData;

    @BeforeEach
    void setUp() {
        bootStrapData = new BootStrapData(beerRepository, customerRepository, beerCsvService);
    }

    @Test
    void testData() throws Exception {
        bootStrapData.run();

        assertThat(beerRepository.count()).isEqualTo(2414);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}