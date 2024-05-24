package com.eubrunoo07.ecommerce.api.repositories;

import com.eubrunoo07.ecommerce.api.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
}
