package org.bradmoore.monitor.integration;

import org.bradmoore.monitor.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created on 4/24/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
// don't want to text myself every time i run tests
@Ignore
public class TextMessageIntegrationTest {
	@Resource(name = "textMessageChannel")
	private MessageChannel textMessageChannel;

	@Test
	public void testMessageSend() {
		MessagingTemplate template = new MessagingTemplate();

		template.send(textMessageChannel, new GenericMessage<>("an integration test just happened."));
	}
}
