package com.github.bosamatheus.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private EmailRepository repository;

    public Email send(final Email email) {
        email.setSentAt(LocalDateTime.now());
        final var msg = new SimpleMailMessage();
        msg.setFrom(email.getEmailFrom());
        msg.setTo(email.getEmailTo());
        msg.setSubject(email.getSubject());
        msg.setText(email.getText());
        try {
            emailSender.send(msg);
            email.setStatus(StatusEmailEnum.SENT);
        } catch (MailException e) {
            email.setStatus(StatusEmailEnum.ERROR);
        }
        return repository.save(email);
    }
}
