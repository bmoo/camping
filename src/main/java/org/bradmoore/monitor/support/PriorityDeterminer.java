package org.bradmoore.monitor.support;

import org.bradmoore.monitor.domain.DayAvailability;
import org.bradmoore.monitor.domain.Site;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class PriorityDeterminer {

	@Resource(name = "prioritySites")
	private int[] prioritySites;

	@Resource(name = "highPrioritySites")
	private int[] highPrioritySites;

	@Resource(name = "priorityDates")
	private LocalDate[] highPriorityDates;

	public boolean isHighPriority(Site site) {
		final int siteNumber = site.getSiteNumber();

		if (Arrays.asList(highPrioritySites).contains(siteNumber)) {
			return true;
		} else if (Arrays.asList(prioritySites).contains(siteNumber)) {
			return doesSiteContainHighPriorityDates(site);
		} else {
			return false;
		}
	}

	public boolean isPriority(Site site) {
		final int siteNumber = site.getSiteNumber();

		if (Arrays.asList(prioritySites).contains(siteNumber)) {
			return true;
		} else {
			return doesSiteContainHighPriorityDates(site);
		}

	}

	protected boolean doesSiteContainHighPriorityDates(Site site) {
		final List<DayAvailability> days = site.getDays();
		if (days == null) {
			return false;
		}

		for (DayAvailability day : days) {
			if (isDateHighPriority(day.getDate())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isDateHighPriority(LocalDate checkDate) {
		for (LocalDate hpDate : highPriorityDates) {
			if (checkDate.isEqual(hpDate)) {
				return true;
			}
		}
		return false;
	}
}
