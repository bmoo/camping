package org.bradmoore.monitor.domain;

import java.util.ArrayList;
import java.util.List;

public class Site {

	private int siteNumber;
	private List<DayAvailability> days;

	public Site(int siteNumber) {
		this.siteNumber = siteNumber;
	}

	public List<DayAvailability> getDays() {
		if (days == null) {
			days = new ArrayList<>();
		}
		return days;
	}

	public void addDay(DayAvailability day) {
		getDays().add(day);

	}

	public void setDays(List<DayAvailability> days) {
		this.days = days;
	}

	public Integer getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(Integer siteNumber) {
		this.siteNumber = siteNumber;
	}

}
