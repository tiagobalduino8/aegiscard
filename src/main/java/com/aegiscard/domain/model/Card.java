package com.aegiscard.domain.model;

import java.util.UUID;

public class Card {
    private final UUID id;
    private final String number;

    public Card(UUID id, String number) {
        this.id = id;
        this.number = number;
    }

    public UUID getId() { return id; }
    public String getNumber() { return number; }
}
