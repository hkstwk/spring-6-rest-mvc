package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.entities.Beer;
import com.hkstwk.spring6restmvc.exceptions.NotFoundException;
import com.hkstwk.spring6restmvc.mappers.BeerMapper;
import com.hkstwk.spring6restmvc.model.BeerDTO;
import com.hkstwk.spring6restmvc.repositories.BeerRepository;
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
class BeerControllerIT {
    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

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
        UUID uuid = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> beerController.getBeerById(uuid));
    }

    @Rollback
    @Transactional
    @Test
    void testSaveNewBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("My integration test beer")
                .build();

        ResponseEntity responseEntity = beerController.saveNewBeer(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[locationUUID.length - 1]);
        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();
    }

    @Test
    void updateBeerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void testUpdateBeerById() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "My updated integration test beer name";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteByIdFound() {
        Beer beer = beerRepository.findAll().getFirst();

        ResponseEntity responseEntity = beerController.deleteById(beer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(beerRepository.findById(beer.getId())).isEmpty();
    }

    @Test
    void testDeleteByIdNotFound() {
        UUID uuid = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> beerController.deleteById(uuid));
    }
}