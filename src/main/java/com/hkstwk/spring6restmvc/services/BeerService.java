package com.hkstwk.spring6restmvc.services;

import com.hkstwk.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
}
