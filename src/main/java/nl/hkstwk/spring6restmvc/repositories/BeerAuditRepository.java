package nl.hkstwk.spring6restmvc.repositories;

import nl.hkstwk.spring6restmvc.entities.BeerAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerAuditRepository extends JpaRepository<BeerAudit, UUID>{
}
