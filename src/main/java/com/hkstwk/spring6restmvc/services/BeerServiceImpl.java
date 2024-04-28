package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.Beer;
import com.hkstwk.spring6restmvc.model.BeerStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Triple Karmeliet")
                .beerStyle(BeerStyle.TRIPLE)
                .upc("123456")
                .quantityOnHand(50)
                .price(BigDecimal.valueOf(3.49))
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
