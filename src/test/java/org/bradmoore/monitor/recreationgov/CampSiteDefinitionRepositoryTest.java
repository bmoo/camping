package org.bradmoore.monitor.recreationgov;

import org.bradmoore.monitor.Application;
import org.bradmoore.monitor.domain.entity.CampSiteDefinition;
import org.bradmoore.monitor.domain.repository.CampSiteDefinitionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created on 6/19/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
public class CampSiteDefinitionRepositoryTest {

	@Autowired
	private CampSiteDefinitionRepository campSiteDefinitionRepository;

	@Test
	public void testThatCampSiteDefinitionRepositoryWorks() {
		Iterable<CampSiteDefinition> campSiteDefinitions = campSiteDefinitionRepository.findAll();

		assertThat(campSiteDefinitions.iterator(), is(notNullValue()));
		assertThat(campSiteDefinitions.iterator().hasNext(), is(equalTo(true)));
	}
}
