package com.github.bosamatheus.email.adapters.config;

import com.github.bosamatheus.EmailApplication;
import com.github.bosamatheus.email.application.ports.EmailRepositoryPort;
import com.github.bosamatheus.email.application.ports.EmailTransferServicePort;
import com.github.bosamatheus.email.application.services.EmailServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = EmailApplication.class)
public class BeanConfig {

    @Bean
    public EmailServiceImpl emailServiceImpl(final EmailTransferServicePort emailTransferService, final EmailRepositoryPort emailRepository) {
        return new EmailServiceImpl(emailTransferService, emailRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
