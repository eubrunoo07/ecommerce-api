package com.eubrunoo07.ecommerce.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    @NotBlank(message = "User field cannot be empty")
    private String user;
    private List<ProductOrderDTO> products;
    @NotBlank(message = "Payment type field cannot be empty")
    private String paymentType;
    private boolean useCoupon;
    private String coupon;

}
