package com.hkstwk.spring6restmvc.repositories;

import com.hkstwk.spring6restmvc.entities.Beer;
import com.hkstwk.spring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveBeer(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My new beer")
                        .beerStyle(BeerStyle.TRIPLE)
                        .price(BigDecimal.valueOf(12.35))
                        .upc("my upc")
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getBeerName()).isEqualTo("My new beer");
        assertThat(savedBeer.getId()).isNotNull();
    }

}