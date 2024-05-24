package com.eubrunoo07.ecommerce.api.services;

import com.eubrunoo07.ecommerce.api.dtos.request.ShoppingCartRequestDTO;
import com.eubrunoo07.ecommerce.api.entities.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart save(ShoppingCart shoppingCart);

    void createCart(ShoppingCartRequestDTO dto);
}
