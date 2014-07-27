package org.bradmoore.camping.web.http;

import org.bradmoore.camping.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ActiveApiDataRetrievalTest {

	@Autowired
	ActiveApiDataRetrieval activeApiDataRetrieval;

	@Test
	public void testUrlBuilder() {
		fail("unimplemented");
	}
}