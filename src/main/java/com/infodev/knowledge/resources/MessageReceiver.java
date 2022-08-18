package com.infodev.knowledge.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

    private final Queue queue;

    public MessageReceiver(Queue queue) {
        this.queue = queue;
    }

    @RabbitListener(queues = "#{queue.getName()}")
    public void getMsg(final String message) {
        LOGGER.info("Getting messages.....");
        LOGGER.info("Finally Receiver received the message and the message  is..\n" + message);
    }

}

