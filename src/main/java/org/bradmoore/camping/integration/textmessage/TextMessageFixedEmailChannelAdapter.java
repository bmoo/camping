package org.bradmoore.camping.integration.textmessage;

import org.bradmoore.camping.support.EmailMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created on 4/23/14.
 */
@MessageEndpoint
public class TextMessageFixedEmailChannelAdapter implements EmailMessageSender {

	@Value("${message.text.maildomain}")
	protected String mailDomain;

	@Value("${message.text.to}")
	protected String toRecipient;

	@Autowired
	protected FixedEmailChannelAdapter fixedEmailChannelAdapter;

	private String buildTextRecipient() {
		return toRecipient + "@" + mailDomain;
	}

	@Override
	public void sendMessageWithSubject(String message, String subjectPrefix) {
		fixedEmailChannelAdapter.sendMessage(message, buildTextRecipient(), subjectPrefix);
	}

	@ServiceActivator(inputChannel = "textMessageChannel", autoStartup = "${spring.integration.text-message.start}")
	public void sendMessage(String message) {
		fixedEmailChannelAdapter.sendMessage(message, buildTextRecipient(), "");
	}
}
