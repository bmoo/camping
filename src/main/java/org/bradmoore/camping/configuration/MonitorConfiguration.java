package org.bradmoore.camping.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.messaging.MessageChannel;

import java.util.Properties;

/**
 * Created on 4/21/14.
 */
@Configuration
@EnableIntegration
@Import({ FilterConfiguration.class, PriorityConfiguration.class,
  RecreationConfiguration.class })
public class MonitorConfiguration {

	@Bean
	public MailSender mailSender(@Value("${mail.host}") String host, @Value("${mail.port}") int port,
	  @Value("${mail.protocol}") String protocol, @Value("${mail.username}") String userName,
	  @Value("${mail.password}") String password, Properties javaMailProperties) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(host);
		mailSender.setUsername(userName);
		mailSender.setPassword(password);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}

	@Bean
	public Properties javaMailProperties(@Value("${mail.smtp.auth}") String auth,
	  @Value("${mail.smtp.starttls.enable}")
	  String tlsEnable, @Value("${mail.smtp.quitwait}") String quitWait) {

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", auth);
		properties.setProperty("mail.smtp.starttls.enable", tlsEnable);
		properties.setProperty("mail.smtp.quitwait", quitWait);

		return properties;
	}

	@Bean
	public MessageChannel textMessageChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel sitesChannel() {
		return new DirectChannel();
	}
}
