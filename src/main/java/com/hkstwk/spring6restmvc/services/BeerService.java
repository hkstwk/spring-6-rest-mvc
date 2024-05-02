package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Optional<Beer> getBeerById(UUID id);
    List<Beer> listBeers();
    Beer saveNewBeer(Beer beer);
    void updateById(UUID beerId, Beer beer);
    void deleteById(UUID beerId);
    void patchById(UUID beerId, Beer beer);
}

