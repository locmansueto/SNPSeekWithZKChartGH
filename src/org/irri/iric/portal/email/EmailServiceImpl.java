package org.irri.iric.portal.email;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

	private JavaMailSender emailSender;

	static final int PORT = 587;

	static final String FROM = "snpseek@irri.org";

	static final String FROMNAME = "snpseek";

	static final String SMTP_USERNAME = "AKIAIKQ5546KL2FEJ6EQ";

	static final String SMTP_PASSWORD = "AjpkcrrfH+EttKBdNjrPvmZtSy+mvTZUrV+UCUJClWtD";

	static final String HOST = "email-smtp.us-west-2.amazonaws.com";

	@Override
	public JavaMailSender getJavaMailSender() {

		if (emailSender != null)
			return emailSender;

		// JavaMailSender mailSender = new JavaMailSenderImpl();
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("systems.irri.org");
		mailSender.setPort(8080);

		mailSender.setUsername("snpseek@systems.irri.org");
		// mailSender.setPassword("password");

		/*
		 * systems.irri.org port 8080 from email: snpseek@systems.irri.org
		 */

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "false");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.debug", "true");

		emailSender = mailSender;

		mailSender.createMimeMessage();

		return emailSender;
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * SimpleMailMessage message = new SimpleMailMessage();
		 * message.setTo(to); message.setSubject(subject);
		 * message.setText(text); getJavaMailSender().send(message);
		 */

		MimeMessage message = getJavaMailSender().createMimeMessage();
		// message.setHeader("Message-ID", snpseek@systems.irri.org");
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		// helper.setCc("l.mansueto@irri.org");
		helper.setFrom("snpseek@irri.org", "IRRI Seed Ordering");
		helper.setSubject(subject);
		// helper.setText(text);
		message.setContent(text, "text/html");
		getJavaMailSender().send(message);

	}

	// @Override
	// public void sendSimpleMessage(List<String> to, List<String> cc, String
	// subject, String text) throws Exception {
	// // TODO Auto-generated method stub
	//
	//
	// //String strto[]=new String[to.ize()];
	// Set<String> lto=new HashSet();
	// for(int i=0; i<to.size(); i++) {
	// String toi=to.get(i).trim();
	// if(toi.isEmpty()) continue;
	// lto.add(toi);
	// }
	// String strto[]=new String[lto.size()];
	// int icnt=0;
	// for(String toi:lto) {
	// strto[icnt]=toi;
	// icnt++;
	// }
	//
	// MimeMessage message = getJavaMailSender().createMimeMessage();
	// //message.setHeader("Message-ID", snpseek@systems.irri.org");
	// MimeMessageHelper helper = new MimeMessageHelper(message, true);
	// helper.setTo(strto);
	//
	// Set<String> lcc=new HashSet();
	// if(cc!=null && !cc.isEmpty()) {
	// for(int i=0; i<cc.size(); i++) {
	// String toi=cc.get(i).trim();
	// if(toi.isEmpty()) continue;
	// lcc.add(toi);
	// }
	// String strcc[]=new String[lcc.size()];
	// icnt=0;
	// for(String toi:lcc) {
	// strcc[icnt]=toi;
	// icnt++;
	// }
	// helper.setCc(strcc);
	// }
	// AppContext.debug("sending to:" + lto +" cc:" + lcc);
	//
	// //helper.setCc("l.mansueto@irri.org");
	// helper.setFrom("snpseek@systems.irri.org","IRRI Seed Ordering");
	// helper.setSubject(subject);
	// //helper.setText(text);
	// message.setContent( text, "text/html");
	// getJavaMailSender().send(message);
	//
	// /*
	// SimpleMailMessage message = new SimpleMailMessage();
	// //SimpleMessageHelper helper = new MimeMessageHelper(message, true);
	// message.setTo( to.toArray(new String[to.size()]));
	// if(cc!=null && !cc.isEmpty()) message.setCc( cc.toArray(new
	// String[cc.size()]));
	// message.setSubject(subject);
	//
	// message.setText(text);
	// getJavaMailSender().send(message);
	// */
	// }

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment,
			String filename) throws Exception {
		// TODO Auto-generated method stub

		MimeMessage message = getJavaMailSender().createMimeMessage();
		// message.setHeader("Message-ID", snpseek@systems.irri.org");
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setCc("l.mansueto@irri.org");
		helper.setFrom("snpseek@irri.org", "IRRI Seed Ordering");
		helper.setSubject(subject);
		helper.setText(text);
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment(filename, file);

		getJavaMailSender().send(message);
	}

	@Override
	public void sendSimpleMessage(List<String> to, List<String> cc, String subject, String text) throws Exception {

		InternetAddress[] recipients = new InternetAddress[to.size()];
		int i = 0;
		for (String recipient : to)
			recipients[i++] = new InternetAddress(recipient);
			
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipients(Message.RecipientType.TO, recipients);
		msg.setSubject(subject);
		msg.setContent(text, "text/html");

		Transport transport = session.getTransport();

		try {
			System.out.println("Sending...");

			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} finally {
			// Close and terminate the connection.
			transport.close();
		}
	}

}
