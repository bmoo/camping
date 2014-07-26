package org.bradmoore.monitor.support;


import javax.annotation.Resource;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import org.bradmoore.monitor.domain.AvailabilityStatusCode;
import org.bradmoore.monitor.domain.DayAvailability;

@Component
public class DayAvailabilityBuilder {

	@Resource(name = "startDate")
	private LocalDate startDay;


	public DayAvailability getDayAvailabilityWithOffsetAndCode(int offset,
			String code) {
		final AvailabilityStatusCode statusCode = AvailabilityStatusCode
				.valueOfByCode(code);
		final LocalDate date = getDateFromOffset(offset);
		return new DayAvailability(statusCode, date);
	}

	private LocalDate getDateFromOffset(int offset) {
		return LocalDate.now().plus(Days.days(offset));
	}
}
