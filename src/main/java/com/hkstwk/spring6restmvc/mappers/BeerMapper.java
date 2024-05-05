package com.hkstwk.spring6restmvc.mappers;

import com.hkstwk.spring6restmvc.entities.Beer;
import com.hkstwk.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDTOToBeer(BeerDTO beerDTO);
    BeerDTO beerToBeerDTO(Beer beer);
}
