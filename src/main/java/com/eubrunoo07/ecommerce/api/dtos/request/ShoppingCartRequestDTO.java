package com.eubrunoo07.ecommerce.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartRequestDTO {
    @NotBlank(message = "User field cannot be empty")
    private String user;
    @NotNull(message = "Items field cannot be empty")
    private List<CartItemRequestDTO> items;
}
