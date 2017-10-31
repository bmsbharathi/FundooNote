package com.bridgeit.note.testpackage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendMail {

	@Autowired
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@RequestMapping("dummy")
	public void dummyMethod() {
	
		System.out.println("Hellooooooo");
	}
	
	@RequestMapping("sendMail")
	public void sendMail() {
			
		System.out.println("Hello");
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true);
			mimeHelper.setFrom("bmsbharathi@gmail.com");
			mimeHelper.setTo("bmsbharathi@gmail.com");
			mimeHelper.setText("Hello...Hello");
			mimeHelper.setSubject("Welcome");
			mailSender.send(message);
			
		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

}
