package org.bradmoore.camping.integration.campsite;

import org.apache.commons.lang3.StringUtils;
import org.bradmoore.camping.support.EmailMessageSender;
import org.bradmoore.camping.support.PriorityDeterminer;
import org.bradmoore.camping.web.xml.domain.SiteResult;
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
	public void notifyOfAnyAvailableSites(List<SiteResult> sites) {

		final List<String> normalMessages = new ArrayList<>();
		final List<String> priorityMessages = new ArrayList<>();
		final List<String> highPriorityMessages = new ArrayList<>();

		for (SiteResult site : sites) {

			String message = buildSiteMessageListForSite(site);

			routeSiteMessagesToPriorityList(normalMessages, priorityMessages, highPriorityMessages, site, message);
		}
		sendHighPriorityMessage(highPriorityMessages);
		sendPriorityMessage(priorityMessages);
		sendNormalMessage(normalMessages);
	}

	private void routeSiteMessagesToPriorityList(List<String> normalMessages, List<String> priorityMessages,
	  List<String> highPriorityMessages, SiteResult site, String message) {
		if (priorityDeterminer.isHighPriority(site)) {
			highPriorityMessages.add(StringUtils.join(message, ""));
		} else if (priorityDeterminer.isPriority(site)) {
			priorityMessages.add(StringUtils.join(message, ""));
		} else {
			normalMessages.add(StringUtils.join(message, ""));
		}
	}

	private String buildSiteMessageListForSite(SiteResult site) {
		return "Site [" + site.getSite() + "] has availability";
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
			messageSender.sendMessageWithSubject(concatenateMessage(messages), subjectPrefix);
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
