package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.entities.Beer;
import com.hkstwk.spring6restmvc.exceptions.NotFoundException;
import com.hkstwk.spring6restmvc.model.BeerDTO;
import com.hkstwk.spring6restmvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeers() {
        List<BeerDTO> beerDTOList = beerController.listBeers();
        assertThat(beerDTOList).hasSize(4);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyListBeers() {
        beerRepository.deleteAll();
        List<BeerDTO> beerDTOList = beerController.listBeers();
        assertThat(beerDTOList).isEmpty();
    }

    @Test
    void testGetBeerById() {
        Beer beer = beerRepository.findAll().getFirst();
        BeerDTO beerDTO = beerController.getBeerById(beer.getId());
        assertThat(beerDTO).isNotNull();
    }

    @Test
    void testGetBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> beerController.getBeerById(UUID.randomUUID()));
    }
}