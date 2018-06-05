package com.mdrsolutions.SpringJmsExample;

import org.apache.activemq.ActiveMQConnectionFactory;
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
		Sender sender = context.getBean(Sender.class);
        sender.sendMessage("order-queue", "Hello");
	}

    @Bean
    public JmsListenerContainerFactory warehouseFactory(ConnectionFactory factory, DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory containerFactory configurer =  new DefaultJmsListenerContainerFactory();
        configurer.configure(containerFactory, factory);

        return containerFactory;
    }

    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(connectionFactory());
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory());
	    return factory;
    }


}
