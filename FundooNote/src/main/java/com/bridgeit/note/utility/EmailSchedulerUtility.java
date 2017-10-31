package com.bridgeit.note.utility;

import com.bridgeit.note.model.User;

public class EmailSchedulerUtility implements Runnable {

	User user;
	EmailUtility emailService;

	public EmailSchedulerUtility(User user, EmailUtility emailService) {
		this.user = user;
		this.emailService = emailService;
	}

	public void run() {
		System.out.println("Sending Email");
		emailService.sendEmailsuccess(user);
	}

}
