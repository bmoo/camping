package org.bradmoore.camping.support;

import org.bradmoore.camping.web.xml.domain.SiteResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class PriorityDeterminer {

	private int[] prioritySites;

	private int[] highPrioritySites;

	public boolean isHighPriority(SiteResult site) {
		final int siteNumber = site.getSite();

		if (Arrays.asList(highPrioritySites).contains(siteNumber)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPriority(SiteResult site) {
		final int siteNumber = site.getSite();

		if (Arrays.asList(prioritySites).contains(siteNumber)) {
			return true;
		} else {
			return false;
		}

	}
}
