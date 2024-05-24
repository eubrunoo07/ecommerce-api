package com.eubrunoo07.ecommerce.api.dtos.response;

import com.eubrunoo07.ecommerce.api.enums.OrderStatus;
import com.eubrunoo07.ecommerce.api.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String id;
    private UserOrderResponseDTO user;
    private List<OrderItemResponseDTO> items;
    private String status;
    private BigDecimal total;
    private String paymentType;
    private boolean useCoupon;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime datePlaced;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime arrivalForecast;
    private String coupon;
}
