package org.bradmoore.camping.support;

/**
 * Created on 5/21/14.
 */
public interface EmailMessageSender {
	void sendMessageWithSubject(String message,
	  String subjectPrefix);
}
