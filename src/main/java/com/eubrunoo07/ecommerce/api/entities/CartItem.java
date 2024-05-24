package com.eubrunoo07.ecommerce.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cartItem_tb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column
    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;

}
