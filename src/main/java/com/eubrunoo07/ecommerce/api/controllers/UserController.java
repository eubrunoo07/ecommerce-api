package com.eubrunoo07.ecommerce.api.controllers;

import com.eubrunoo07.ecommerce.api.dtos.request.CartItemRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.request.UserRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.*;
import com.eubrunoo07.ecommerce.api.entities.CartItem;
import com.eubrunoo07.ecommerce.api.entities.Order;
import com.eubrunoo07.ecommerce.api.entities.OrderItem;
import com.eubrunoo07.ecommerce.api.entities.User;
import com.eubrunoo07.ecommerce.api.services.OrderService;
import com.eubrunoo07.ecommerce.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecommerce/api/users")
@CrossOrigin(originPatterns = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody@Valid UserRequestDTO dto){
        //Cria instância de User
        User user = new User();

        //Copia informações do DTO para minha Entidade
        BeanUtils.copyProperties(dto, user);
        user.setOrders(new ArrayList<>());

        //Verifica se o nome não está incompleto
        String[] nameSplited = user.getName().split(" ");
        if(nameSplited.length < 2){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name field must be 'full name'");
        }

        //Por fim, salva o user e retorna sucesso
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody@Valid UserRequestDTO dto, @PathVariable String id){
        //Verificando se User existe
        User user = userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        //Copiando informações do DTO para o User porém ignorando a propriedade "id"
        BeanUtils.copyProperties(dto, user, "id");

        //Por fim salvando o user e retornando sucesso
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<UserResponseDTO> users(){
        //Pegando todos os usuarios do banco
        List<User> users = userService.findAll();

        //Criando uma lista DTO do user
        List<UserResponseDTO> dtos = new ArrayList<>();

        //Substituindo a entidade em um DTO para retorno
        for (User user : users) {
            //Adicionando cada user para DTO
            dtos.add(UserResponseDTO
                    .builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .build());
        }
        return dtos;
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDTO>> getUserOrders(@PathVariable String id) {
        // Verifica se o usuário com o ID fornecido existe
        User user = userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Obtém todos os pedidos associados a esse usuário
        List<Order> orders = orderService.findByUser(user);
        //Criando lista de DTO para a resposta
        List<OrderResponseDTO> response = new ArrayList<>();
        for (Order order : orders) {
            //Criando o userOrderResponseDTO que é necessário para o OrderResponseDTO
            UserOrderResponseDTO userOrderResponseDTO = UserOrderResponseDTO
                    .builder()
                    .name(order.getUser().getName())
                    .email(order.getUser().getEmail())
                    .address(order.getUser().getAddress())
                    .build();

            //Criando o orderItemResponseDTO que é necessário também para a criação do OrderResponseDTO
            List<OrderItemResponseDTO> orderItems = new ArrayList<>();

            //Para cada orderItem achado, substituir para o DTO
            for (OrderItem orderItem : order.getOrders()) {
                OrderItemResponseDTO orderItemResponseDTO = OrderItemResponseDTO
                        .builder()
                        .productId(orderItem.getProduct().getId())
                        .productName(orderItem.getProduct().getName())
                        .quantity(orderItem.getQuantity())
                        .build();
                orderItems.add(orderItemResponseDTO);
            }

            //Adicionando todas  as informações para nosso DTO
            response.add(OrderResponseDTO
                    .builder()
                    .id(order.getId())
                    .user(userOrderResponseDTO)
                    .total(order.getTotal())
                    .items(orderItems)
                    .status(order.getStatus().getDescription())
                    .build());
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}/cart")
    public ResponseEntity<UserCartResponseDTO> getUserCart(@PathVariable String id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<CartItem> cartItems = user.getShoppingCart().getItems();

            List<CartItemRequestDTO> cartItemDTOs = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                ProductResponseDTO productDTO = ProductResponseDTO.builder()
                        .id(cartItem.getProduct().getId())
                        .build();

                CartItemRequestDTO cartItemDTO = CartItemRequestDTO.builder()
                        .product(productDTO.getId())
                        .quantity(cartItem.getQuantity())
                        .build();

                cartItemDTOs.add(cartItemDTO);
            }

            UserCartResponseDTO userCartResponseDTO = UserCartResponseDTO.builder()
                    .userId(user.getId())
                    .cartItems(cartItemDTOs)
                    .userName(user.getName())
                    .build();

            return ResponseEntity.ok(userCartResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
