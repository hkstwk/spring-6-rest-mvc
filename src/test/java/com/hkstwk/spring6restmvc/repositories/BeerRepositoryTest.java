package com.hkstwk.spring6restmvc.repositories;

import com.hkstwk.spring6restmvc.bootstrap.BootStrapData;
import com.hkstwk.spring6restmvc.entities.Beer;
import com.hkstwk.spring6restmvc.model.BeerStyle;
import com.hkstwk.spring6restmvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({BootStrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveBeer() {
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

    @Test
    void saveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            beerRepository.save(Beer.builder()
                    .beerName("My new beer is way too long 1234567890123456789012345678901234567890")
                    .beerStyle(BeerStyle.TRIPLE)
                    .price(BigDecimal.valueOf(12.35))
                    .upc("my upc")
                    .build());

            beerRepository.flush();
        });
    }

    @Test
    void listGetBeerListByName() {
        List<Beer> beerList = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");

        assertThat(beerList).hasSize(336);
    }
}