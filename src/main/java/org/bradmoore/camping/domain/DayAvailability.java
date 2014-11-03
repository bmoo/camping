package org.bradmoore.camping.domain;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

public class DayAvailability {

	private AvailabilityStatus availabilityStatus;
	private LocalDate date;

	public DayAvailability(AvailabilityStatus availabilityStatus,
			LocalDate date) {
		this.availabilityStatus = availabilityStatus;
		this.date = date;
	}

	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}
	
	public boolean isAvailable() {
		return availabilityStatus == AvailabilityStatus.AVAILABLE;
	}

	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
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
