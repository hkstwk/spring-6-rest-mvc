package nl.hkstwk.spring6restmvc.events;

import nl.hkstwk.spring6restmvc.entities.Beer;
import org.springframework.security.core.Authentication;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerEvent {

        Beer getBeer();

        Authentication getAuthentication();
}
