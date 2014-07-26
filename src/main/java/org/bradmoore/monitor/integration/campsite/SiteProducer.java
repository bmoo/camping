package org.bradmoore.monitor.integration.campsite;

import org.bradmoore.monitor.domain.Site;
import org.bradmoore.monitor.web.http.RecreationGovDataRetrieval;
import org.bradmoore.monitor.web.xml.RecreationGovPageParser;
import org.bradmoore.monitor.support.RecreationGovSiteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/11/14.
 */
@Component
public class SiteProducer {
	@Autowired
	private RecreationGovDataRetrieval retriever;

	@Autowired
	private RecreationGovPageParser parser;

	@Autowired
	private RecreationGovSiteFilter siteFilter;

	@InboundChannelAdapter(poller = @Poller(fixedRate = "300000", maxMessagesPerPoll = "1"), value = "sitesChannel")
	public List<Site> produceSiteList() {
		List<String> webPages = retriever.getXmlSiteLists();
		List<Site> allSites = new ArrayList<>();

		for (String page : webPages) {
			allSites.addAll(parser.parsePageForSites(page));
		}

		return siteFilter.filterSites(allSites);
	}
}
