package org.bradmoore.monitor.support;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DateHelper {

	public static LocalDate buildDate(int monthIndexedAtOne, int day, int year) {
		return new LocalDate(year, monthIndexedAtOne, day);
	}

	public static LocalDate buildDateUsingDaysFromDate(LocalDate date, int days) {
		return date.plus(Days.days(days));
	}
}
