package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.mappers.BeerMapper;
import com.hkstwk.spring6restmvc.model.BeerDTO;
import com.hkstwk.spring6restmvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJpaImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMapper.beerToBeerDTO(beerRepository
                .findById(id)
                .orElse(null))
        );
    }

    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDTO)
                .toList();
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBeer(beer)));
    }

    @Override
    public void updateById(UUID beerId, BeerDTO beer) {
        beerRepository.findById(beerId).ifPresent(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            beerRepository.save(foundBeer);
        });
    }

    @Override
    public void deleteById(UUID beerId) {

    }

    @Override
    public void patchById(UUID beerId, BeerDTO beer) {

    }
}
