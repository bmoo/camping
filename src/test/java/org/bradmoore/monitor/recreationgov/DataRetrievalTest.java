package org.bradmoore.monitor.recreationgov;

import static org.junit.Assert.*;

import java.util.List;

import org.bradmoore.monitor.Application;
import org.bradmoore.monitor.web.http.RecreationGovDataRetrieval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class DataRetrievalTest {

	@Autowired
	RecreationGovDataRetrieval sut;

	@Test
	public void testGettingWebPages() {
		// in order to work correctly, this needs a camp site definition injected into it
		
		List<String> webPages = sut.getXmlSiteLists();
		assertNotNull(webPages);
		assertEquals(10, webPages.size());
	}
}
