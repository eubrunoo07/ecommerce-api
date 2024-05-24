package com.eubrunoo07.ecommerce.api.dtos.response;

import com.eubrunoo07.ecommerce.api.entities.Order;
import com.eubrunoo07.ecommerce.api.entities.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
}
