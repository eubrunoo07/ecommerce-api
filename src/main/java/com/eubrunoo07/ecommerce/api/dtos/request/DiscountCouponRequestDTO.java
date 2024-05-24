package com.eubrunoo07.ecommerce.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCouponRequestDTO {
    @NotBlank(message = "Code field cannot be empty")
    private String code;
    @NotBlank(message = "Discount type field cannot be empty")
    private String discountType;
    @NotNull(message = "Discount value cannot be empty")
    private BigDecimal discountValue;
    @NotNull(message = "Expiry date cannot be empty")
    private String expiryDate;
    @NotNull(message = "Usage limit cannot be empty")
    private Integer usageLimit;
}
