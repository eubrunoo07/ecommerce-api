package com.eubrunoo07.ecommerce.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_tb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column
    private Integer quantityInStock;
    @Column
    private List<String> images;
    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
