package com.github.bosamatheus.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService service;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload final EmailDto emailDto) {
        final var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        final var sent = service.send(email);
        System.out.printf("Email %s status: %s%n", sent.getEmailId(), sent.getStatus());
    }
}
