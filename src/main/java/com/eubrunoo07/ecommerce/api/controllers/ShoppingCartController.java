package com.eubrunoo07.ecommerce.api.controllers;

import com.eubrunoo07.ecommerce.api.dtos.request.CartItemRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.request.ShoppingCartRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.ShoppingCartResponseDTO;
import com.eubrunoo07.ecommerce.api.entities.CartItem;
import com.eubrunoo07.ecommerce.api.entities.Product;
import com.eubrunoo07.ecommerce.api.entities.ShoppingCart;
import com.eubrunoo07.ecommerce.api.entities.User;
import com.eubrunoo07.ecommerce.api.services.CartItemService;
import com.eubrunoo07.ecommerce.api.services.ProductService;
import com.eubrunoo07.ecommerce.api.services.ShoppingCartService;
import com.eubrunoo07.ecommerce.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ecommerce/api/carts")
@CrossOrigin(originPatterns = "*")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCart(@RequestBody@Valid ShoppingCartRequestDTO dto){
        cartService.createCart(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public List<ShoppingCartResponseDTO> allCartOfUser(@PathVariable String id){
        User user = userService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));


        ShoppingCart userShoppingCart = user.getShoppingCart();

        List<CartItem> userCartItems = userShoppingCart.getItems();
        List<CartItemRequestDTO> cartItems = new ArrayList<>();
        for(CartItem cartItem : userCartItems){
            CartItemRequestDTO cartItemDTO = CartItemRequestDTO
                    .builder()
                    .product(cartItem.getProduct().getId())
                    .quantity(cartItem.getQuantity())
                    .build();
            cartItems.add(cartItemDTO);
        }

        ShoppingCartResponseDTO response = ShoppingCartResponseDTO
                .builder()
                .cartId(userShoppingCart.getId())
                .cartItems(cartItems)
                .total(userShoppingCart.getTotal())
                .build();
        return Collections.singletonList(response);
    }
}
