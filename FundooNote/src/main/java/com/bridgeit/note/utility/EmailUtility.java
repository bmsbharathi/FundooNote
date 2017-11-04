package com.bridgeit.note.utility;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.bridgeit.note.model.User;

/*
 * 	A Utility class to send Mail and generate OTP when Password is forgotten
 * 
 */

@Service
public class EmailUtility {

	@Autowired
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	// Generating a Random String as OTP
	public String generateotp() {

		String alpha = "bharathibms";
		String numbers = "0123456789";
		String otp = alpha + numbers;
		char[] password = new char[8];
		for (int i = 0; i < 8; i++) {

			password[i] = otp.charAt(new Random().nextInt(otp.length()));
		}

		return new String(password);
	}

	// Sending the OTP to the user to reset password
	public void sendEmail(User user) {

		String otp = generateotp();

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true);
			mimeHelper.setFrom("bmsbharathi@gmail.com");
			mimeHelper.setTo(user.getEmail());
			mimeHelper.setText("Hello...Your OTP is " + otp);
			mimeHelper.setSubject("Did you forget your password??");
			mailSender.send(message);

		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	// Sending the welcome mail when the user is successfully signed in
	public void sendEmailsuccess(User user) {

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true);
			mimeHelper.setFrom("bmsbharathi@gmail.com");
			mimeHelper.setTo(user.getEmail());
			mimeHelper.setText("You have successfully registered with FundooNote");
			mimeHelper.setSubject("Welcome");
			mailSender.send(message);

		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}

}
