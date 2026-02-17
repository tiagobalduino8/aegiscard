package com.aegiscard.domain.repository;

import com.aegiscard.domain.model.Card;

import java.util.Optional;

public interface CardRepository {
    void save(Card card);
    Optional<Card> findByNumber(String number);
}
