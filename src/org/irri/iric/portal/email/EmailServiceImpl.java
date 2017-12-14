package org.irri.iric.portal.email;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.irri.iric.portal.AppContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

	private JavaMailSender emailSender;
    
    @Override
    public JavaMailSender getJavaMailSender() {
    	
    	if(emailSender!=null) return emailSender;
    	
        //JavaMailSender mailSender = new JavaMailSenderImpl();
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        mailSender.setHost("systems.irri.org");
        mailSender.setPort(8080);
         
        mailSender.setUsername("snpseek@systems.irri.org");
        //mailSender.setPassword("password");
        
/*
systems.irri.org
port 8080
from email: snpseek@systems.irri.org
*/
         
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.debug", "true");
         
        emailSender=mailSender;
        
        mailSender.createMimeMessage();
        
        return emailSender;
    }
    
	@Override
	public void sendSimpleMessage(String to, String subject, String text)  throws Exception {
		// TODO Auto-generated method stub
		/*
		 	SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(to);
	        message.setSubject(subject); 
	        message.setText(text);
	        getJavaMailSender().send(message);
	      */  
	        
			  MimeMessage message =  getJavaMailSender().createMimeMessage();
			  //message.setHeader("Message-ID", snpseek@systems.irri.org");
			  MimeMessageHelper helper = new MimeMessageHelper(message, true);
			  helper.setTo(to);
			  helper.setCc("l.mansueto@irri.org");
			  helper.setFrom("snpseek@systems.irri.org","IRRI Seed Ordering");
			  helper.setSubject(subject);
			  //helper.setText(text);
			  message.setContent( text, "text/html");
			  getJavaMailSender().send(message);
			  
	}
	
	@Override
	public void sendSimpleMessage(List<String> to, List<String> cc,  String subject, String text) throws Exception {
		// TODO Auto-generated method stub
		
		
		 //String strto[]=new String[to.ize()];
		 Set<String> lto=new HashSet(); 
		 for(int i=0; i<to.size(); i++) {
			 String toi=to.get(i).trim();
			 if(toi.isEmpty()) continue;
			 lto.add(toi);
		 }
		 String strto[]=new String[lto.size()];
		 int icnt=0;
		 for(String toi:lto) {
			 strto[icnt]=toi;
			icnt++; 
		 }
		 
		  MimeMessage message =  getJavaMailSender().createMimeMessage();
		  //message.setHeader("Message-ID", snpseek@systems.irri.org");
		  MimeMessageHelper helper = new MimeMessageHelper(message, true);
		  helper.setTo(strto);

		  Set<String> lcc=new HashSet(); 
		  if(cc!=null && !cc.isEmpty()) {
				 for(int i=0; i<cc.size(); i++) {
					 String toi=cc.get(i).trim();
					 if(toi.isEmpty()) continue;
					 lcc.add(toi);
				 }
				 String strcc[]=new String[lto.size()];
				 icnt=0;
				 for(String toi:lcc) {
					 strto[icnt]=toi;
					icnt++; 
				 }				 
				 helper.setCc(strcc);
		  }
		  AppContext.debug("sending to:" + lto +" cc:" + lcc);

		  //helper.setCc("l.mansueto@irri.org");
		  helper.setFrom("snpseek@systems.irri.org","IRRI Seed Ordering");
		  helper.setSubject(subject);
		  //helper.setText(text);
		  message.setContent( text, "text/html");
		  getJavaMailSender().send(message);
		
		/*
		 	SimpleMailMessage message = new SimpleMailMessage(); 
		 	//SimpleMessageHelper helper = new MimeMessageHelper(message, true);
		 	message.setTo( to.toArray(new String[to.size()]));
		 	if(cc!=null && !cc.isEmpty()) message.setCc( cc.toArray(new String[cc.size()]));
	        message.setSubject(subject);
	        
	        message.setText(text);
	        getJavaMailSender().send(message);
	        */
	}


	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment, String filename) throws Exception {
		// TODO Auto-generated method stub

		
		  MimeMessage message =  getJavaMailSender().createMimeMessage();
		  //message.setHeader("Message-ID", snpseek@systems.irri.org");
		  MimeMessageHelper helper = new MimeMessageHelper(message, true);
		  helper.setTo(to);
		  helper.setCc("l.mansueto@irri.org");
		  helper.setFrom("snpseek@systems.irri.org","IRRI Seed Ordering");
		  helper.setSubject(subject);
		  helper.setText(text);
		  FileSystemResource file  = new FileSystemResource(new File(pathToAttachment));
		  helper.addAttachment(filename, file);
		  
		  
		
		  getJavaMailSender().send(message);
	}

	
}
