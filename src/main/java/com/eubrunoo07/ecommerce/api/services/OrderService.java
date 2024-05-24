package com.eubrunoo07.ecommerce.api.services;

import com.eubrunoo07.ecommerce.api.dtos.request.OrderRequestDTO;
import com.eubrunoo07.ecommerce.api.entities.Order;
import com.eubrunoo07.ecommerce.api.entities.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(OrderRequestDTO dto);

    Optional<Order> findById(String id);

    List<Order> findByUser(User user);
}
