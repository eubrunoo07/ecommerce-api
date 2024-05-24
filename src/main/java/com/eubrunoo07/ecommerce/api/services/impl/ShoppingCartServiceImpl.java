package com.eubrunoo07.ecommerce.api.services.impl;


import com.eubrunoo07.ecommerce.api.dtos.request.CartItemRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.request.ShoppingCartRequestDTO;
import com.eubrunoo07.ecommerce.api.entities.CartItem;
import com.eubrunoo07.ecommerce.api.entities.Product;
import com.eubrunoo07.ecommerce.api.entities.ShoppingCart;
import com.eubrunoo07.ecommerce.api.entities.User;
import com.eubrunoo07.ecommerce.api.repositories.CartItemRepository;
import com.eubrunoo07.ecommerce.api.repositories.ProductRepository;
import com.eubrunoo07.ecommerce.api.repositories.ShoppingCartRepository;
import com.eubrunoo07.ecommerce.api.repositories.UserRepository;
import com.eubrunoo07.ecommerce.api.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return cartRepository.save(shoppingCart);
    }

    @Override
    public void createCart(ShoppingCartRequestDTO dto) {
        User user = findByIdOrThrowNotFound(dto.getUser());

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (Objects.equals(shoppingCart, null)){
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
        }

        for (CartItemRequestDTO cartItemDto : dto.getItems()) {
            updateCartItemQuantity(shoppingCart, cartItemDto);
        }

        updateShoppingCartTotal(shoppingCart);

        shoppingCart = cartRepository.save(shoppingCart);

        user.setShoppingCart(shoppingCart);
        userRepository.save(user);
    }

    private User findByIdOrThrowNotFound(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private void updateCartItemQuantity(ShoppingCart shoppingCart, CartItemRequestDTO cartItemDto) {
        Optional<CartItem> cartItemOptional = shoppingCart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartItemDto.getProduct()))
                .findFirst();

        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
        } else {
            Product product = productRepository
                    .findById(cartItemDto.getProduct())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            CartItem cartItem = new CartItem();
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setProduct(product);
            cartItem.setShoppingCart(shoppingCart);
            shoppingCart.getItems().add(cartItem);
        }
    }

    private void updateShoppingCartTotal(ShoppingCart shoppingCart) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : shoppingCart.getItems()) {
            total = total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        shoppingCart.setTotal(total);
    }
}