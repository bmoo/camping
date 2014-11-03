package org.bradmoore.camping.support;

import org.bradmoore.camping.web.xml.domain.SiteResult;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecreationGovSiteFilter {

	private final Integer startSiteNumber;
	private final Integer endSiteNumber;

	@Autowired
	public RecreationGovSiteFilter(
			@Qualifier("startSiteNumber") Integer startSiteNumber,
			@Qualifier("endSiteNumber") Integer endSiteNumber) {
		this.startSiteNumber = startSiteNumber;
		this.endSiteNumber = endSiteNumber;
	}

	public List<SiteResult> filterSites(List<SiteResult> sites) {
		return sites.stream().filter(site -> site.getSite() >= startSiteNumber && site.getSite() <= endSiteNumber).collect(Collectors.toList());
	}
}
