package com.eubrunoo07.ecommerce.api.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterDTO {
    private String name;
    private String category;
}
