package org.bradmoore.monitor.domain.repository;

import org.bradmoore.monitor.domain.entity.ReservationRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created on 6/19/14.
 */
@RepositoryRestResource(collectionResourceRel = "id", path = "reservationRequest")
public interface ReservationRequestRepository extends CrudRepository<ReservationRequest, Integer> {

}
