package com.github.bosamatheus.email.application.domain;

import com.github.bosamatheus.email.application.domain.enums.StatusEmailEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Email {

    private UUID emailId;
    private String owner;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sentAt;
    private StatusEmailEnum status;
}
