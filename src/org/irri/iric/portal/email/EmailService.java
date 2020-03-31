package org.irri.iric.portal.email;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.zkoss.zul.Label;

public interface EmailService {
	public JavaMailSender getJavaMailSender();

	public void sendSimpleMessage(String to, String subject, String text) throws Exception;

	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment,
			String filename) throws Exception;

	public void sendSimpleMessage(List<String> to, List<String> cc, String subject, String text) throws Exception;
	
	public void sendSimpleMessage(List<String> to, List<String> cc, String subject, String text, List<Label> images) throws Exception;
	
	

}
