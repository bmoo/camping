package org.bradmoore.camping.integration.campsite;

import org.bradmoore.camping.support.RecreationGovSiteFilter;
import org.bradmoore.camping.web.http.ActiveApiDataRetrieval;
import org.bradmoore.camping.web.xml.ActiveApiResponseParser;
import org.bradmoore.camping.web.xml.domain.CampSitesResult;
import org.bradmoore.camping.web.xml.domain.SiteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 6/11/14.
 */
@Component
public class SiteProducer {
	@Autowired
	private ActiveApiDataRetrieval retriever;

	@Autowired
	private ActiveApiResponseParser parser;

	@Autowired
	private RecreationGovSiteFilter siteFilter;

	@InboundChannelAdapter(poller = @Poller(fixedRate = "30000", maxMessagesPerPoll = "1"), value = "sitesChannel",
	  autoStartup = "${spring.integration.active-api.start}")
	public List<CampSitesResult> produceSiteList() {
		List<InputStream> webPages = retriever.getXmlSiteLists();

		final List<CampSitesResult> results = webPages.parallelStream().map(page -> parser.parseCampSiteApiResponseXmlString
		  (page)).collect(Collectors.toList());

		return results;

//		return siteFilter.filterSites(allSites);
	}
}
