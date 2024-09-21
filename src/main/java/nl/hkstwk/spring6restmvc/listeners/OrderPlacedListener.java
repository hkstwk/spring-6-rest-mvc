package nl.hkstwk.spring6restmvc.listeners;

import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.spring6restmvcapi.events.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderPlacedListener {

    @Async
    @EventListener
    public void listen(OrderPlacedEvent orderPlacedEvent){

        // kafka stuff here later
        log.info("Order placed event received: {}", orderPlacedEvent.getBeerOrderDTO());
    }
}
