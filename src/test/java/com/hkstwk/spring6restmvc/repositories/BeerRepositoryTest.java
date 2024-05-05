package com.hkstwk.spring6restmvc.repositories;

import com.hkstwk.spring6restmvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveBeer(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My new beer")
                .build());

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getBeerName()).isEqualTo("My new beer");
        assertThat(savedBeer.getId()).isNotNull();
    }

}