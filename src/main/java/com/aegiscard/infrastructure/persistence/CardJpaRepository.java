package com.aegiscard.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardJpaRepository extends JpaRepository<CardEntity, UUID> {
    Optional<CardEntity> findByNumber(String number);
}

