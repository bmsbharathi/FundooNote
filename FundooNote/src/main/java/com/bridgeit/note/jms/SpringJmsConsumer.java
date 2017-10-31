package com.bridgeit.note.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.bridgeit.note.utility.EmailUtility;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class SpringJmsConsumer {

	@Autowired
	EmailUtility email;

	private Destination destination;
	private JmsTemplate jmsTemplate;

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}


}
