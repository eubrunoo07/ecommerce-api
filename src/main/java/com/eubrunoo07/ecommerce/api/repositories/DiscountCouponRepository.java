package com.eubrunoo07.ecommerce.api.repositories;

import com.eubrunoo07.ecommerce.api.entities.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, String> {
    Optional<DiscountCoupon> findByCode(String coupon);
}
