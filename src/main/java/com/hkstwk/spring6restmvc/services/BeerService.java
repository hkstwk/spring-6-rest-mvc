package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.BeerDTO;
import com.hkstwk.spring6restmvc.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Optional<BeerDTO> getBeerById(UUID id);
    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Integer pageNumber, Integer pageSize);
    BeerDTO saveNewBeer(BeerDTO beer);
    Optional<BeerDTO> updateById(UUID beerId, BeerDTO beer);
    Boolean deleteById(UUID beerId);
    Optional<BeerDTO> patchById(UUID beerId, BeerDTO beer);
}

