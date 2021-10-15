package com.github.bosamatheus.email.adapters.outbound.persistence;

import com.github.bosamatheus.email.adapters.outbound.persistence.entities.EmailEntity;
import com.github.bosamatheus.email.application.domain.Email;
import com.github.bosamatheus.email.application.domain.PageInfo;
import com.github.bosamatheus.email.application.ports.EmailRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public record PostgresEmailRepository(SpringDataEmailRepository repository, ModelMapper modelMapper) implements EmailRepositoryPort {

    @Override
    public Email save(final Email email) {
        final var emailEntity = repository.save(modelMapper.map(email, EmailEntity.class));
        return modelMapper.map(emailEntity, Email.class);
    }

    @Override
    public List<Email> findAll(final PageInfo pageInfo) {
        final Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        return repository.findAll(pageable).stream()
                .map(entity -> modelMapper.map(entity, Email.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Email> findById(final UUID emailId) {
        return repository.findById(emailId)
                .map(entity -> modelMapper.map(entity, Email.class));
    }
}
