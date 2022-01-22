package controller;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("mail")
	public class SendMail {

	    @Autowired
	    private JavaMailSender emailSender;

	    public void sendSimpleMessage(
	      String to, String subject, String text) {

	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("khanvms9@gmail.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        emailSender.send(message);

	    }
	    public void sendMessageWithAttachment(
	      String to, String subject, String text, String pathToAttachment) throws Exception {

	        
	        MimeMessage message = emailSender.createMimeMessage();
	         
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        
	        helper.setFrom("khanvms9@gmail.com");
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(text);
	            
	        FileSystemResource file 
	          = new FileSystemResource(new File(pathToAttachment));
	        helper.addAttachment("Invoice.pdf", file);

	        emailSender.send(message);
	     
	    }
	}

