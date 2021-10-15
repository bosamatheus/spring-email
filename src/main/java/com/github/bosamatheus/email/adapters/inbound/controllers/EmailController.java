package com.github.bosamatheus.email.adapters.inbound.controllers;

import com.github.bosamatheus.email.adapters.dto.EmailDto;
import com.github.bosamatheus.email.application.domain.Email;
import com.github.bosamatheus.email.application.domain.PageInfo;
import com.github.bosamatheus.email.application.services.EmailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public record EmailController(EmailServiceImpl service) {

    @PostMapping("/emails")
    public ResponseEntity<Email> send(@RequestBody @Valid final EmailDto emailDto) {
        final var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        return new ResponseEntity<>(service.send(email), HttpStatus.CREATED);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<Email>> list(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) final Pageable pageable) {
        final var page = new PageInfo();
        BeanUtils.copyProperties(pageable, page);
        final var emails = service.findAll(page);
        return new ResponseEntity<>(new PageImpl<Email>(emails, pageable, emails.size()), HttpStatus.OK);
    }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> get(@PathVariable(value = "emailId") final UUID emailId) {
        return service.findById(emailId)
                .<ResponseEntity<Object>>map(email -> ResponseEntity.status(HttpStatus.OK).body(email))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found."));
    }
}
