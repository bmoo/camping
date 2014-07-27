package org.bradmoore.camping.web.xml;

import org.bradmoore.camping.Application;
import org.bradmoore.camping.domain.Site;
import org.bradmoore.camping.web.xml.domain.CampSitesResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ActiveApiResponseParserTest {
	public static final String SAMPLE_CAMP_SITE_RESPONSE_XML_FILE = "sample-camp-site-response.xml";

	@Autowired
	ActiveApiResponseParser activeApiResponseParser;

	@Test
	public void testParsePageForSites() throws Exception {
		final String apiResponse = this.readSampleResponseAsXmlString();

		final CampSitesResult campSitesResult = activeApiResponseParser.parseCampSiteApiResponseXmlString(apiResponse);

		assertThat(campSitesResult).isNotNull();
		assertThat(campSitesResult.getSiteResults()).isNotNull();
		assertThat(campSitesResult.getSiteResults()).isNotEmpty();

	}

	public String readSampleResponseAsXmlString() {
		Resource resource = new ClassPathResource(SAMPLE_CAMP_SITE_RESPONSE_XML_FILE);

		try {
			byte[] encoded = Files.readAllBytes(resource.getFile().toPath());
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Can't read test input file");
		}
	}
}