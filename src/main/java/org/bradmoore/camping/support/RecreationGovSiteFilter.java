package org.bradmoore.camping.support;

import org.bradmoore.camping.domain.DayAvailability;
import org.bradmoore.camping.domain.Site;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecreationGovSiteFilter {

	private Integer startSiteNumber;
	private Integer endSiteNumber;
	private LocalDate startDate;
	private LocalDate endDate;

	@Autowired
	public RecreationGovSiteFilter(
			@Qualifier("startSiteNumber") Integer startSiteNumber,
			@Qualifier("endSiteNumber") Integer endSiteNumber,
			@Qualifier("startDate") LocalDate startDate,
			@Qualifier("endDate") LocalDate endDate) {
		this.startSiteNumber = startSiteNumber;
		this.endSiteNumber = endSiteNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public List<Site> filterSites(List<Site> sites) {

		final List<Site> filteredSites = new ArrayList<>();
		for (Site site : sites) {
			final int siteNumber = site.getSiteNumber();
			if (siteNumber >= startSiteNumber && siteNumber <= endSiteNumber) {
				Site siteWithFilteredOutDays = removeUndesiredDatesFromSite(site);
				if (siteWithFilteredOutDays.getDays() != null
						&& siteWithFilteredOutDays.getDays().size() > 0) {
					filteredSites.add(siteWithFilteredOutDays);
				}
			}
		}

		return filteredSites;

	}

	protected Site removeUndesiredDatesFromSite(Site site) {
		final List<DayAvailability> days = site.getDays();
		final List<DayAvailability> newDays = new ArrayList<>();
		if (days != null) {
			for (DayAvailability day : days) {
				final LocalDate actualDate = day.getDate();
				if (isDateWithinDesiredRange(actualDate)) {
					newDays.add(day);
				}
			}
		}
		site.setDays(newDays);
		return site;
	}

	protected boolean isDateWithinDesiredRange(LocalDate actualDate) {
		final boolean isBeforeEndDate = checkDateBeforeOrEqualToDay(actualDate,
				endDate);
		final boolean isAfterStartDate = checkDateBeforeOrEqualToDay(startDate,
				actualDate);
		return isAfterStartDate && isBeforeEndDate;
	}

	protected boolean checkDateBeforeOrEqualToDay(LocalDate checkDate,
			LocalDate againstDate) {
		return checkDate.isBefore(againstDate) || checkDate.isEqual(againstDate);
	}

}
