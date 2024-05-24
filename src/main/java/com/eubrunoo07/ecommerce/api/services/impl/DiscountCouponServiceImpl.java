package com.eubrunoo07.ecommerce.api.services.impl;

import com.eubrunoo07.ecommerce.api.entities.DiscountCoupon;
import com.eubrunoo07.ecommerce.api.repositories.DiscountCouponRepository;
import com.eubrunoo07.ecommerce.api.services.DiscountCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCouponServiceImpl implements DiscountCouponService {

    @Autowired
    private DiscountCouponRepository couponRepository;


    @Override
    public DiscountCoupon save(DiscountCoupon discountCoupon) {
        return couponRepository.save(discountCoupon);
    }

    @Override
    public List<DiscountCoupon> findAll() {
        return couponRepository.findAll();
    }
}
