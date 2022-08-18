package com.infodev.knowledge.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);


    public final RabbitTemplate rabbitTemplate;
    public final Binding binding;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, Binding binding) {
        this.rabbitTemplate = rabbitTemplate;
        this.binding = binding;
    }

    @PostMapping(value = "/send")
    @ResponseStatus(code = HttpStatus.OK)
    public String send(@RequestBody final String message) {
        LOGGER.info("Sending message to the queue.");
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message);
        LOGGER.info("Message sent successfully to the queue!!!");
        return "Great!! your message is sent";
    }

}