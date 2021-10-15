package com.github.bosamatheus.email.application.services;

import com.github.bosamatheus.email.application.domain.Email;
import com.github.bosamatheus.email.application.domain.PageInfo;
import com.github.bosamatheus.email.application.domain.enums.StatusEmailEnum;
import com.github.bosamatheus.email.application.ports.EmailRepositoryPort;
import com.github.bosamatheus.email.application.ports.EmailServicePort;
import com.github.bosamatheus.email.application.ports.EmailTransferServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class EmailServiceImpl implements EmailServicePort {

    private final EmailTransferServicePort emailTransferService;
    private final EmailRepositoryPort emailRepository;

    @Override
    public Email send(final Email email) {
        email.setSentAt(LocalDateTime.now());
        try {
            emailTransferService.send(email);
            email.setStatus(StatusEmailEnum.SENT);
        } catch (MailException e) {
            email.setStatus(StatusEmailEnum.ERROR);
        }
        return save(email);
    }

    @Override
    public Email save(final Email email) {
        return emailRepository.save(email);
    }

    @Override
    public List<Email> findAll(final PageInfo pageInfo) {
        return emailRepository.findAll(pageInfo);
    }

    @Override
    public Optional<Email> findById(final UUID emailId) {
        return emailRepository.findById(emailId);
    }
}
