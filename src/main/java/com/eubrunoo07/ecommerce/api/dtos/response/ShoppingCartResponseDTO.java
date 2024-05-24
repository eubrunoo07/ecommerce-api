package com.eubrunoo07.ecommerce.api.dtos.response;

import com.eubrunoo07.ecommerce.api.dtos.request.CartItemRequestDTO;
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
public class ShoppingCartResponseDTO {
    private String cartId;
    private List<CartItemRequestDTO> cartItems;
    private BigDecimal total;
}
