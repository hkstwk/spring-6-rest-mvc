package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.exceptions.NotFoundException;
import com.hkstwk.spring6restmvc.model.Beer;
import com.hkstwk.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> patchById(@PathVariable(name = "beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.patchById(beerId, beer);
        log.debug("Patched beer with id {}, called in {}", beerId, this.getClass().getSimpleName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> deleteById(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
        log.debug("Deleted beer with id {}, called in {}", beerId, this.getClass().getSimpleName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Beer> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.updateById(beerId, beer);
        log.debug("Updated beer with id {}, called in {}", beerId, this.getClass().getSimpleName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<Beer> saveNewBeer(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH.concat("/").concat(savedBeer.getId().toString()));
        log.debug("Post new beer with id {}, called in {}", savedBeer.getId(), this.getClass().getSimpleName());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<Beer> listBeers() {
        log.debug("Get list of beer, called in {}", this.getClass().getSimpleName());
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get beer by beerId {}, called in {}", beerId, this.getClass().getSimpleName());
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }
}
