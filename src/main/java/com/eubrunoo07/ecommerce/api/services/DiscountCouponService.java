package com.eubrunoo07.ecommerce.api.services;

import com.eubrunoo07.ecommerce.api.entities.DiscountCoupon;

import java.util.List;

public interface DiscountCouponService {
    DiscountCoupon save(DiscountCoupon discountCoupon);

    List<DiscountCoupon> findAll();
}
