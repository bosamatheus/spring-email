package com.github.bosamatheus.email.adapters.outbound.smtp;

import com.github.bosamatheus.email.application.domain.Email;
import com.github.bosamatheus.email.application.ports.EmailTransferServicePort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public record SmtpEmailTransferService(JavaMailSender sender) implements EmailTransferServicePort {

    @Override
    public void send(final Email email) {
        final var msg = new SimpleMailMessage();
        msg.setFrom(email.getEmailFrom());
        msg.setTo(email.getEmailTo());
        msg.setSubject(email.getSubject());
        msg.setText(email.getText());
        sender.send(msg);
    }
}
