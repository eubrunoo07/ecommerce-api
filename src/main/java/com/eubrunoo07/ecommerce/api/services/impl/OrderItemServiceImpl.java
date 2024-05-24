package com.eubrunoo07.ecommerce.api.services.impl;

import com.eubrunoo07.ecommerce.api.repositories.OrderItemRepository;
import com.eubrunoo07.ecommerce.api.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
}
