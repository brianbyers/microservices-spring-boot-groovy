package com.dfwgg.sample

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories
class Application {
	final static String queueName = "reward-user-queue";
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("reward-user-exchange");
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}
	
	@Bean
	SimpleMessageConverter converter(){
		return new SimpleMessageConverter();
	}

	static main(args) {
		SpringApplication.run(Application.class, args);
	}

}
