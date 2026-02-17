package com.aegiscard.infrastructure.persistence;

import com.aegiscard.domain.model.Card;
import com.aegiscard.domain.repository.CardRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private final CardJpaRepository jpa;

    public CardRepositoryImpl(CardJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(Card card) {
        CardEntity entity = new CardEntity();
        entity.setId(card.getId());
        entity.setNumber(card.getNumber());
        jpa.save(entity);
    }

    @Override
    public Optional<Card> findByNumber(String number) {
        return jpa.findByNumber(number)
                  .map(e -> new Card(e.getId(), e.getNumber()));
    }
}
