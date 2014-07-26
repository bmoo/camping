package org.bradmoore.monitor.web.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bradmoore.monitor.support.DayAvailabilityBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.bradmoore.monitor.domain.AvailabilityStatusCode;
import org.bradmoore.monitor.domain.DayAvailability;
import org.bradmoore.monitor.domain.Site;

@Component
public class RecreationGovPageParser {

	Logger logger = LoggerFactory.getLogger(RecreationGovPageParser.class);

	@Autowired
	private DayAvailabilityBuilder dayBuilder;

	public List<Site> parsePageForSites(String html) {
		logger.trace(html);

		final List<Site> sites = new ArrayList<>();

		try {
			final Document doc = Jsoup.parse(html);

			final Element element = doc.getElementById("calendar");

			final Elements rows = element.getElementsByTag("tr");

			for (Element row : rows) {

				final Elements headers = row.getElementsByClass("calendar");
				if (headers != null && headers.size() > 0)
					for (Element ignored : headers) {

					}

				final Elements sitename = row.getElementsByClass("sn");
				if (sitename != null && sitename.size() > 0) {
					final Element siteNumberDiv = sitename.first();
					final Elements sitelist = siteNumberDiv
							.getElementsByClass("siteListLabel");
					final String siteNumber = sitelist.first().text();
					// System.out.println("SITE: " + siteNumber);

					final Site site = new Site(Integer.parseInt(siteNumber));
					// NumberUtils.toInt(siteNumber));

					final Elements statusElements = row
							.getElementsByClass("status");
					if (statusElements != null && statusElements.size() > 0) {
						for (int i = 0; i < statusElements.size(); i++) {
							Element status = statusElements.get(i);
							Set<String> classNames = status.classNames();
							for (String cssClass : classNames) {
								if (AvailabilityStatusCode
										.valueOfByCode(cssClass) != null) {
									DayAvailability day = dayBuilder
											.getDayAvailabilityWithOffsetAndCode(
													i, cssClass);
									site.addDay(day);
								}
							}
						}
					}

					sites.add(site);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sites;
	}

}
