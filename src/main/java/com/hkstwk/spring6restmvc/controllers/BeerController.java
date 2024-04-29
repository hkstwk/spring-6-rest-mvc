package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.model.Beer;
import com.hkstwk.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PostMapping
    public ResponseEntity<Beer> handlePostt(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);
        return new ResponseEntity<>(savedBeer, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Beer> listBeers() {
        log.debug("Get list of beer, called in {}", this.getClass().getSimpleName());
        return beerService.listBeers();
    }

    @GetMapping("{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get beer by beerId {}, called in {}", beerId, this.getClass().getSimpleName());
        return beerService.getBeerById(beerId);
    }
}
