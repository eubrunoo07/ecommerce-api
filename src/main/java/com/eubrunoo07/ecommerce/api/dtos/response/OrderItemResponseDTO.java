package com.eubrunoo07.ecommerce.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
