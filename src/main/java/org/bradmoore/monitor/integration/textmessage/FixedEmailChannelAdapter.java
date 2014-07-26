package org.bradmoore.monitor.integration.textmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created on 4/22/14.
 */
@MessageEndpoint
public class FixedEmailChannelAdapter {
	private Logger logger = LoggerFactory.getLogger(FixedEmailChannelAdapter.class);

	protected MailSender mailSender;

	@Autowired
	public FixedEmailChannelAdapter(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@ServiceActivator
	public void sendMessage(String message, String toRecipient, String subject) {
		logger.info("Sending message '{}'", message);

		SimpleMailMessage simpleMailMessage = createMessage(message, toRecipient, subject);

		mailSender.send(simpleMailMessage);

		logger.debug("Message sent");
	}

	public SimpleMailMessage createMessage(String message, String toRecipient, String subject) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toRecipient);
		simpleMailMessage.setText(message);
		simpleMailMessage.setSubject(subject);

		return simpleMailMessage;
	}
}
