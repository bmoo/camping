package org.bradmoore.camping.configuration;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecreationConfiguration {

	private static final long MINUTE_IN_MILLIS = 1000 * 60;
	private static final long WAIT_INTERVAL_RANGE_BEGIN = MINUTE_IN_MILLIS * 3; // 3
																				// minutes
	private static final long WAIT_INTERVAL_RANGE_END = MINUTE_IN_MILLIS * 5; // 5
																				// minutes

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern(
	  "MM/dd/yyyy");

	@Bean(name = "recreationGovWaitIntervalRangeStart")
	public Long getThreadWaitIntervalRangeStart() {
		return WAIT_INTERVAL_RANGE_BEGIN;
	}

	@Bean(name = "recreationGovWaitIntervalRangeEnd")
	public Long getThreadWaitIntervalRangeEnd() {
		return WAIT_INTERVAL_RANGE_END;
	}

	@Bean(name = "recreationGovDateFormat")
	public DateTimeFormatter getDateFormat() {
		return DATE_FORMAT;
	}

}
