package org.irri.iric.portal.email;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


public interface EmailService {
	public JavaMailSender getJavaMailSender();
	public void sendSimpleMessage( String to, String subject, String text) throws Exception;
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment, String filename) throws Exception;
	public void sendSimpleMessage(List<String> to, List<String> cc, String subject, String text) throws Exception;

}
