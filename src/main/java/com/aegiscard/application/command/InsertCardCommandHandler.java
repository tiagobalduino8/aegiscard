package com.aegiscard.application.command;

import com.aegiscard.domain.model.Card;
import com.aegiscard.domain.repository.CardRepository;
import com.aegiscard.domain.service.CardEncryptionService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InsertCardCommandHandler {
    private final CardRepository repository;
    private final CardEncryptionService encryptionService;

    public InsertCardCommandHandler(CardRepository repository, CardEncryptionService encryptionService) {
        this.repository = repository;
        this.encryptionService = encryptionService;
    }

    public UUID handle(InsertCardCommand command) {
        String encrypted = encryptionService.encrypt(command.number());
        Card card = new Card(UUID.randomUUID(), encrypted);
        repository.save(card);
        return card.getId();
    }
}
