package com.eubrunoo07.ecommerce.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Name field cannot be empty")
    private String name;
    @NotBlank(message = "Description field cannot be empty")
    private String description;
    @NotNull(message = "Price field cannot be empty")
    private BigDecimal price;
    @NotNull(message = "Stock field cannot be empty")
    private Integer quantityInStock;
    @NotNull(message = "Images field cannot be empty")
    private List<String> images;
    @NotBlank(message = "Category field cannot be empty")
    private String category;
    @NotBlank(message = "User field cannot be empty")
    private String user;
}
