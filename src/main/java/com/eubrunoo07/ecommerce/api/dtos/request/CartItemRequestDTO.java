package com.eubrunoo07.ecommerce.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDTO {
    @NotBlank(message = "Product field cannot be empty")
    private String product;
    @NotNull(message = "Quantity field cannot be empty")
    private Integer quantity;

}
