package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.BeerDTO;
import com.hkstwk.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
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

        BeerDTO beer2 = BeerDTO.builder()
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

        BeerDTO beer3 = BeerDTO.builder()
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

        BeerDTO beer4 = BeerDTO.builder()
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
    public Optional<BeerDTO> getBeerById(UUID id) {
        log.debug("Get beer by id {}, called in {}", id, this.getClass().getSimpleName());
        return Optional.ofNullable(beerMap.get(id));
    }

    @Override
    public List<BeerDTO> listBeers() {
        log.debug("Get list of beer, called in {}", this.getClass().getSimpleName());
        return new ArrayList<>(this.beerMap.values());
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);
        log.debug("Save new beer with id {}, called in {}", savedBeer.getId(), this.getClass().getSimpleName());

        return savedBeer;
    }

    @Override
    public void updateById(UUID beerId, BeerDTO beer) {
        BeerDTO existingBeer = beerMap.get(beerId);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setBeerStyle(beer.getBeerStyle());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());

        beerMap.put(existingBeer.getId(), existingBeer);
        log.debug("Updated beer with id {}, called in {}", beerId, this.getClass().getSimpleName());
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
        log.debug("Deleted beer with id {}, called in {}", beerId, this.getClass().getSimpleName());
    }

    @Override
    public void patchById(UUID beerId, BeerDTO beer) {
        BeerDTO existingBeer = beerMap.get(beerId);

        if (beer.getPrice() != null){
            existingBeer.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null){
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (beer.getUpc() != null){
            existingBeer.setUpc(beer.getUpc());
        }

        if (beer.getBeerStyle() != null){
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }

        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }
    }
}
