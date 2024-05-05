package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.model.BeerDTO;
import com.hkstwk.spring6restmvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}