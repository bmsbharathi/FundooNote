/*package com.bridgeit.note.jms;

import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJMS {

	public static void main(String args[]) throws URISyntaxException, Exception {

		Logger logger = Logger.getLogger(SpringJMS.class);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		try {
			for (int i = 0; i < 10; i++) {
				SpringJmsProducer springJmsProducer = (SpringJmsProducer) context.getBean("springJmsProducer");
				springJmsProducer.sendMessage("Hey Dude");
				logger.info("Inside JMS");
				SpringJmsConsumer springJmsConsumer = (SpringJmsConsumer) context.getBean("springJmsConsumer");
				System.out.println("Consumer recieves " + springJmsConsumer.receiveMessage());

			}
		} finally {
			context.close();
		}

	}
}
*/