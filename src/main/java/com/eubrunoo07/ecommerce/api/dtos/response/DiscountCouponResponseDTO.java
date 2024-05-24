package com.eubrunoo07.ecommerce.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCouponResponseDTO {
    private String id;
    private String code;
    private BigDecimal discountValue;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime expiryDate;
    private Integer usageLimit;
}
