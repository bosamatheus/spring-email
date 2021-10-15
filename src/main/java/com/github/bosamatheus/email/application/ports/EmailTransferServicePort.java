package com.github.bosamatheus.email.application.ports;

import com.github.bosamatheus.email.application.domain.Email;

public interface EmailTransferServicePort {

    void send(Email email);
}
