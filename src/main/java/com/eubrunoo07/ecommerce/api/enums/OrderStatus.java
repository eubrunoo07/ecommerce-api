package com.eubrunoo07.ecommerce.api.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDER_PLACED("Order Placed"),
    AWAITING_PAYMENT("Awaiting Payment"),
    ORDER_PAID("Order Paid"),
    PROCESSING("Processing"),
    ORDER_SHIPPED("Order Shipped"),
    IN_TRANSIT("In Transit"),
    DELIVERED("Delivered"),
    ORDER_CANCELLED("Order Cancelled");

    private final String description;

    OrderStatus(String description){
        this.description = description;
    }
}
