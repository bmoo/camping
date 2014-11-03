package org.bradmoore.camping.configuration;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

	private static final int START_SITE = 188;
	private static final int END_SITE = 240;

	private static final int START_DAY = 19;
	private static final int START_MONTH = 6; // ONE-INDEX
	private static final int START_YEAR = 2014;

	private static final int END_DAY = 25;
	private static final int END_MONTH = 6; // ONE-INDEX
	private static final int END_YEAR = 2014;

	@Bean(name = "startSiteNumber")
	public Integer getStartSiteNumber() {
		return START_SITE;
	}

	@Bean(name = "endSiteNumber")
	public Integer getEndSiteNumber() {
		return END_SITE;
	}

	@Bean(name = "endDate")
	public LocalDate getEndDate() {
		return new LocalDate(END_YEAR, END_MONTH, END_DAY);
	}

	@Bean(name = "startDate")
	public LocalDate getStartDate() {
		return new LocalDate(START_YEAR, START_MONTH, START_DAY);
	}
}
