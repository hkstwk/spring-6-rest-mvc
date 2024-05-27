package com.hkstwk.spring6restmvc.repositories;

import com.hkstwk.spring6restmvc.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, Long> {
}
