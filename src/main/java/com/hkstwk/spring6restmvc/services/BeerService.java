package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.BeerDTO;
import com.hkstwk.spring6restmvc.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Optional<BeerDTO> getBeerById(UUID id);
    List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle);
    BeerDTO saveNewBeer(BeerDTO beer);
    Optional<BeerDTO> updateById(UUID beerId, BeerDTO beer);
    Boolean deleteById(UUID beerId);
    Optional<BeerDTO> patchById(UUID beerId, BeerDTO beer);
}

