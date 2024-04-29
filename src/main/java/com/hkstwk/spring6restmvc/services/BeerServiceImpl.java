package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.Beer;
import com.hkstwk.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer4 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Triple Karmeliet")
                .beerStyle(BeerStyle.TRIPLE)
                .upc("123456")
                .quantityOnHand(50)
                .price(BigDecimal.valueOf(3.49))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
        beerMap.put(beer4.getId(), beer4);
    }

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get beer by id {}, called in {}", id, this.getClass().getSimpleName());
        return beerMap.get(id);
    }

    @Override
    public List<Beer> listBeers() {
        log.debug("Get list of beer, called in {}", this.getClass().getSimpleName());
        return new ArrayList<>(this.beerMap.values());
    }
}
