package com.eubrunoo07.ecommerce.api.entities;

import com.eubrunoo07.ecommerce.api.enums.OrderStatus;
import com.eubrunoo07.ecommerce.api.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_tb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orders;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private BigDecimal total;

    @CreatedDate
    private LocalDateTime datePlaced;

    @Column
    private LocalDateTime arrivalForecast;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column
    private boolean useCoupon;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "discountCoupon_id")
    private DiscountCoupon discountCoupon;

    @Column
    private String coupon;

}
