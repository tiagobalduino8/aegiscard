package com.aegiscard.application.query;

import com.aegiscard.domain.model.Card;
import com.aegiscard.domain.repository.CardRepository;
import com.aegiscard.domain.service.CardEncryptionService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindCardQueryHandler {
	private final CardRepository repository;
	private final CardEncryptionService encryptionService;

	public FindCardQueryHandler(CardRepository repository, CardEncryptionService encryptionService) {
		this.repository = repository;
		this.encryptionService = encryptionService;
	}

	public Optional<UUID> handle(FindCardQuery query) {
		String encrypted = encryptionService.encrypt(query.number());
		return repository.findByNumber(encrypted).map(Card::getId);
	}
}
