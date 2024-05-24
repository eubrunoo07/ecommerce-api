package com.eubrunoo07.ecommerce.api.services;

import com.eubrunoo07.ecommerce.api.dtos.request.ProductReviewRequestDTO;
import com.eubrunoo07.ecommerce.api.entities.ProductReview;

public interface ProductReviewService {
    ProductReview save(ProductReview review);
}
