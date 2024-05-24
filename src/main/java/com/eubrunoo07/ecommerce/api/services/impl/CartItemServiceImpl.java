package com.eubrunoo07.ecommerce.api.services.impl;


import com.eubrunoo07.ecommerce.api.entities.CartItem;
import com.eubrunoo07.ecommerce.api.repositories.CartItemRepository;
import com.eubrunoo07.ecommerce.api.services.CartItemService;
import com.eubrunoo07.ecommerce.api.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
