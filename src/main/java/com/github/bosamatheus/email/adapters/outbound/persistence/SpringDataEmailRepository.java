package com.github.bosamatheus.email.adapters.outbound.persistence;

import com.github.bosamatheus.email.adapters.outbound.persistence.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataEmailRepository extends JpaRepository<EmailEntity, UUID> {
}
