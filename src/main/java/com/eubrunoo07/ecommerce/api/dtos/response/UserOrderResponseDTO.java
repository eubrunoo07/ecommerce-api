package com.eubrunoo07.ecommerce.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderResponseDTO {
    private String name;
    private String email;
    private String address;
}
