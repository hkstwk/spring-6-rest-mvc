package nl.hkstwk.spring6restmvc.events;

import nl.hkstwk.spring6restmvc.entities.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;

/**
 * Created by jt, Spring Framework Guru.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class BeerDeletedEvent implements BeerEvent {
    private Beer beer;

    private Authentication authentication;
}
