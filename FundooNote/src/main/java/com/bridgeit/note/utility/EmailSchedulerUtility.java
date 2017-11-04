package com.bridgeit.note.utility;

import org.apache.log4j.Logger;

import com.bridgeit.note.model.User;

public class EmailSchedulerUtility implements Runnable {

	private User user;
	private EmailUtility emailService;
	private Logger logger = Logger.getLogger(EmailSchedulerUtility.class);
	
	public EmailSchedulerUtility(User user, EmailUtility emailService) {
		
		this.user = user;
		this.emailService = emailService;
	}

	public void run() {
		
		logger.warn("Sending Email");
		emailService.sendEmailsuccess(user);
	}

}
