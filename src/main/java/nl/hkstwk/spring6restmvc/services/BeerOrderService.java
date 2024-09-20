package nl.hkstwk.spring6restmvc.services;

import nl.hkstwk.spring6restmvc.entities.BeerOrder;
import nl.hkstwk.spring6restmvcapi.model.BeerOrderCreateDTO;
import nl.hkstwk.spring6restmvcapi.model.BeerOrderDTO;
import nl.hkstwk.spring6restmvcapi.model.BeerOrderUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerOrderService {

    Optional<BeerOrderDTO> getById(UUID beerOrderId);

    Page<BeerOrderDTO> listOrders(Integer pageNumber, Integer pageSize);

    BeerOrder createOrder(BeerOrderCreateDTO beerOrderCreateDTO);

    BeerOrderDTO updateOrder(UUID beerOrderId, BeerOrderUpdateDTO beerOrderUpdateDTO);

    void deleteOrder(UUID beerOrderId);
}
