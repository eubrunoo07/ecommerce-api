package com.eubrunoo07.ecommerce.api.controllers;

import com.eubrunoo07.ecommerce.api.dtos.request.DiscountCouponRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.DiscountCouponResponseDTO;
import com.eubrunoo07.ecommerce.api.entities.DiscountCoupon;
import com.eubrunoo07.ecommerce.api.enums.DiscountType;
import com.eubrunoo07.ecommerce.api.services.DiscountCouponService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/discounts")
@CrossOrigin(originPatterns = "*")
public class DiscountCouponController {

    @Autowired
    private DiscountCouponService couponService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCoupon(@RequestBody@Valid DiscountCouponRequestDTO dto){

        DiscountCoupon discountCoupon = new DiscountCoupon();
        BeanUtils.copyProperties(dto, discountCoupon);
        discountCoupon.setDiscountType(DiscountType.valueOf(dto.getDiscountType()));
        try{
            discountCoupon.setExpiryDate(LocalDateTime.parse(dto.getExpiryDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        //Por fim, salvamos o cupom
        couponService.save(discountCoupon);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public List<DiscountCouponResponseDTO> coupons(){
        List<DiscountCoupon> coupons = couponService.findAll();
        List<DiscountCouponResponseDTO> couponResponseDTO = new ArrayList<>();
        for (DiscountCoupon coupon : coupons) {
            couponResponseDTO.add(DiscountCouponResponseDTO
                    .builder()
                            .id(coupon.getId())
                            .code(coupon.getCode())
                            .discountValue(coupon.getDiscountValue())
                            .expiryDate(coupon.getExpiryDate())
                            .usageLimit(coupon.getUsageLimit())
                    .build());
        }
        return couponResponseDTO;
    }

}
