package org.bradmoore.monitor.domain;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

public class DayAvailability {

	private AvailabilityStatusCode availabilityStatusCode;
	private LocalDate date;

	public DayAvailability(AvailabilityStatusCode availabilityStatusCode,
			LocalDate date) {
		this.availabilityStatusCode = availabilityStatusCode;
		this.date = date;
	}

	public AvailabilityStatusCode getAvailabilityStatusCode() {
		return availabilityStatusCode;
	}
	
	public boolean isAvailable() {
		return availabilityStatusCode == AvailabilityStatusCode.AVAILABLE;
	}

	public void setAvailabilityStatusCode(
			AvailabilityStatusCode availabilityStatusCode) {
		this.availabilityStatusCode = availabilityStatusCode;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getDayOfMonth() {
		return date.getDayOfMonth();
	}

	public String getDayOfWeek() {
		final int dayOfWeek = date.getDayOfWeek();

		String textOfDay = "";
		switch (dayOfWeek) {
		case DateTimeConstants.SUNDAY:
			textOfDay = "Sunday";
			break;
		case DateTimeConstants.MONDAY:
			textOfDay = "Monday";
			break;
		case DateTimeConstants.TUESDAY:
			textOfDay = "Tuesday";
			break;
		case DateTimeConstants.WEDNESDAY:
			textOfDay = "Wednesday";
			break;
		case DateTimeConstants.THURSDAY:
			textOfDay = "Thursday";
			break;
		case DateTimeConstants.FRIDAY:
			textOfDay = "Friday";
			break;
		case DateTimeConstants.SATURDAY:
			textOfDay = "Saturday";
			break;
		}
		return textOfDay;
	}
}
