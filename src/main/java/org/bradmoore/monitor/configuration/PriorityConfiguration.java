package org.bradmoore.monitor.configuration;

import org.bradmoore.monitor.support.DateHelper;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriorityConfiguration {

	@Bean(name = "prioritySites")
	public int[] getPrioritySites() {
		return new int[] { 237, 235, 233, 231, 229, 227, 225, 223, 221, 219,
				217, 215, 212, 209, 205, 203, 201, 200, 198, 196, 194, 192,
				190, 188, 197 };
	}

	@Bean(name = "highPrioritySites")
	public int[] getHighPrioritySites() {
		return new int[] { 197, 196 };
	}

	@Bean(name = "priorityDates")
	public LocalDate[] getPriorityDates() {
		return new LocalDate[] { DateHelper.buildDate(6, 20, 2014), //
				DateHelper.buildDate(6, 23, 2014), //
				DateHelper.buildDate(6, 24, 2014) //
		};
	}

}
