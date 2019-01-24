package org.irri.iric.portal.email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class CustomMimeMessage extends MimeMessage {

	public CustomMimeMessage(Session session) {
		super(session);
	}

	@Override
	protected void updateMessageID() throws MessagingException {
		setHeader("Message-ID", "objects-message-id");
	}
}
