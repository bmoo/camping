package org.bradmoore.camping.integration.campsite;

import org.apache.commons.lang3.StringUtils;
import org.bradmoore.camping.support.EmailMessageSender;
import org.bradmoore.camping.support.PriorityDeterminer;
import org.bradmoore.camping.domain.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SiteConsumer {

	@Autowired
	private PriorityDeterminer priorityDeterminer;

	@Autowired
	private EmailMessageSender messageSender;

	@ServiceActivator(inputChannel = "sitesChannel", autoStartup = "${spring.integration.active-api.start}")
	public void notifyOfAnyAvailableSites(List<Site> sites) {

		final List<String> normalMessages = new ArrayList<>();
		final List<String> priorityMessages = new ArrayList<>();
		final List<String> highPriorityMessages = new ArrayList<>();

		for (Site site : sites) {

			final List<String> siteMessages = new ArrayList<>();

			buildSiteMessageListForSite(site, siteMessages);

			routeSiteMessagesToPriorityList(normalMessages, priorityMessages, highPriorityMessages, site,
			  siteMessages);
		}
		sendHighPriorityMessage(highPriorityMessages);
		sendPriorityMessage(priorityMessages);
		sendNormalMessage(normalMessages);
	}

	private void routeSiteMessagesToPriorityList(List<String> normalMessages, List<String> priorityMessages,
	  List<String> highPriorityMessages, Site site, List<String> siteMessages) {
		if (!siteMessages.isEmpty()) {
			if (priorityDeterminer.isHighPriority(site)) {
				highPriorityMessages
				  .add(StringUtils.join(siteMessages, ""));
			} else if (priorityDeterminer.isPriority(site)) {
				priorityMessages.add(StringUtils.join(siteMessages, ""));
			} else {
				normalMessages.add(StringUtils.join(siteMessages, ""));
			}
		}
	}

	private void buildSiteMessageListForSite(Site site, List<String> siteMessages) {
		site.getDays().stream().filter(day -> day.isAvailable()).forEach(day -> {
			if (siteMessages.isEmpty()) {
				siteMessages.add("Site [" + site.getSiteNumber() + "] has availability on ");
			} else {
				siteMessages.add(", ");
			}
			siteMessages.add(day.getDayOfWeek() + "-" + day.getDayOfMonth());
		});
	}

	protected void sendHighPriorityMessage(List<String> messages) {
		sendMessage(messages, "! HIGH PRIORITY ! ");
	}

	protected void sendPriorityMessage(List<String> messages) {
		sendMessage(messages, "[PRIORITY] ");
	}

	protected void sendNormalMessage(List<String> messages) {
		sendMessage(messages, "");
	}

	protected void sendMessage(List<String> messages, String subjectPrefix) {
		if (messages.size() > 0) {
			messageSender.sendMessageWithSubject(concatenateMessage(messages),
			  subjectPrefix);
		}
	}

	protected String concatenateMessage(List<String> messages) {
		String ss = "";
		for (String s : messages) {
			ss += s;
			ss += " \n";
		}
		return ss;
	}
}
