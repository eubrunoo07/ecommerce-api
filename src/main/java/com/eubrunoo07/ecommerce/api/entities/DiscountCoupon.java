package com.eubrunoo07.ecommerce.api.entities;

import com.eubrunoo07.ecommerce.api.enums.DiscountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "discountCoupon_tb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String code;
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    @Column
    private BigDecimal discountValue;
    @Column
    private LocalDateTime expiryDate;
    @Column
    private Integer usageLimit;
    @Column
    private Integer usageCount;

}
