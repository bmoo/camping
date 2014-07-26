package org.bradmoore.monitor.recreationgov;

import org.bradmoore.monitor.domain.AvailabilityStatusCode;
import org.bradmoore.monitor.domain.DayAvailability;
import org.bradmoore.monitor.domain.Site;
import org.bradmoore.monitor.support.DateHelper;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class SiteTestHelper {

	public static List<Site> buildSitesInRange(int start, int end) {
		final List<Site> sites = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			sites.add(buildSite(i));
		}
		return sites;
	}

	public static Site buildSite(int siteNumber) {

		return new Site(siteNumber);
	}

	public static void addDaysToSites(List<Site> sites, LocalDate start, int dayCount) {
		for (Site site : sites) {
			for (int i = 0; i < dayCount; i++) {
				final LocalDate date = DateHelper.buildDateUsingDaysFromDate(start,
						i);
				final DayAvailability day = new DayAvailability(
						AvailabilityStatusCode.AVAILABLE, date);
				site.addDay(day);
			}
		}
	}

}
