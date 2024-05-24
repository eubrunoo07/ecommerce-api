package com.eubrunoo07.ecommerce.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewResponseDTO {
    private String id;
    private Integer review;
    private String comments;
    private String userName;
}
