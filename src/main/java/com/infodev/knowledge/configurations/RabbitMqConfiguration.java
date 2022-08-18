package com.infodev.knowledge.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${rabbitmq.queue}")
    private String qName; //TOPIC in case of KAFKA


    @Value("${rabbitmq.exchange}")
    private String exchange;


    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(qName, Boolean.FALSE); //Queue(name,isDurable) - check documentation for more
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(final Queue queue, final TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }

}
