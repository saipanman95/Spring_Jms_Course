package com.mdrsolutions.SpringJmsExample;

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

		System.out.println("Preparing to send a message");
		jmsTemplate.convertAndSend("order-queue", "Hello");
	}

	@Bean
	public JmsListenerContainerFactory warehouseFactory(ConnectionFactory factory, DefaultJmsListenerContainerFactoryConfigurer configurer){
		DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
		configurer.configure(containerFactory, factory);

		return containerFactory;
	}

}
