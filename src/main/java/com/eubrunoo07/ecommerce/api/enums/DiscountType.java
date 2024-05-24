package com.eubrunoo07.ecommerce.api.enums;

import lombok.Getter;

@Getter
public enum DiscountType {
    PERCENTAGE("Percentage"),
    FIXED_AMOUNT("Fixed Amount");

    private final String description;

    DiscountType(String description) {
        this.description = description;
    }
}
