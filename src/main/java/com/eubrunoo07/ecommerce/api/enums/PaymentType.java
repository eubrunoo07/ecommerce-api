package com.eubrunoo07.ecommerce.api.enums;

import lombok.Getter;

@Getter
public enum PaymentType {
    PIX("Pix"),
    CREDIT_CARD("Credit Card"),
    TICKET("Ticket");

    private final String description;
    PaymentType(String description){
        this.description = description;
    }
}
