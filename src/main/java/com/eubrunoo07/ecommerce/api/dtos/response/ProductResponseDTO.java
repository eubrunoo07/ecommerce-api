package com.eubrunoo07.ecommerce.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private List<String> images;
    private BigDecimal price;
    private Integer quantityInStock;
    private String category;
    private String user;
    private List<ProductReviewResponseDTO> reviews;
}
