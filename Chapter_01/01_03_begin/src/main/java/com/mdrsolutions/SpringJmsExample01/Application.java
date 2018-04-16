package com.mdrsolutions.SpringJmsExample01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
@EnableJms
 @SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		System.out.println("Preparing to send an order request to the order-queue");
		jmsTemplate.convertAndSend("order-queue", "customer: 12345, order: 1234");

	}


	@Bean
	public JmsListenerContainerFactory warehouseFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer){
		DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
		configurer.configure(containerFactory, connectionFactory);
		return  containerFactory;
	}


}
