package org.bradmoore.camping.domain.repository;

import org.bradmoore.camping.domain.entity.CampSiteDefinition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created on 6/19/14.
 */
@RepositoryRestResource(path = "campSiteDefinition")
public interface CampSiteDefinitionRepository extends CrudRepository<CampSiteDefinition, Integer> {
}
