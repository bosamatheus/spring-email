package com.github.bosamatheus.email.controller;

import com.github.bosamatheus.email.model.Email;
import com.github.bosamatheus.email.dto.EmailDto;
import com.github.bosamatheus.email.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/email")
    public ResponseEntity<Email> send(@RequestBody @Valid final EmailDto emailDto) {
        final var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        final var sent = service.send(email);
        return new ResponseEntity<>(sent, HttpStatus.CREATED);
    }
}
