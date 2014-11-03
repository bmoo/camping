package org.bradmoore.camping.health;

import org.bradmoore.camping.domain.entity.CampSiteDefinition;
import org.bradmoore.camping.domain.repository.CampSiteDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created on 6/20/14.
 */
@Component
public class CampSiteDefinitionRepositoryHealth implements HealthIndicator {
	@Autowired
	private CampSiteDefinitionRepository campSiteDefinitionRepository;

	@Override
	public Health health() {
		final Iterable<CampSiteDefinition> all = campSiteDefinitionRepository.findAll();

		int count = 0;

		for (CampSiteDefinition ignored : all) {
			count++;
		}

		return Health.status("Found " + count + " camp site definitions").build();
	}
}
