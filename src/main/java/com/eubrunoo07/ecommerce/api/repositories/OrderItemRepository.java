package com.eubrunoo07.ecommerce.api.repositories;

import com.eubrunoo07.ecommerce.api.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}
