package com.eubrunoo07.ecommerce.api.services.impl;

import com.eubrunoo07.ecommerce.api.entities.ProductReview;
import com.eubrunoo07.ecommerce.api.repositories.ProductReviewRepository;
import com.eubrunoo07.ecommerce.api.services.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository reviewRepository;

    @Override
    public ProductReview save(ProductReview review) {
        return reviewRepository.save(review);
    }
}
