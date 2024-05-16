package com.pedroluizforlan.domain.dto;

import com.pedroluizforlan.domain.model.Card;

import java.math.BigDecimal;

public record CardDTO(Long id,
                      String number,
                      BigDecimal limit) {

    public CardDTO(Card model) {
        this(model.getId(), model.getNumber(), model.getLimit());
    }

    public Card toModel() {
        Card model = new Card();
        model.setId(this.id);
        model.setLimit(this.limit);
        model.setNumber(this.number);
        return model;
    }
}
