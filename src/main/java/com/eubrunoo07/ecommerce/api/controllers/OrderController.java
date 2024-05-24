package com.eubrunoo07.ecommerce.api.controllers;

import com.eubrunoo07.ecommerce.api.dtos.request.OrderRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.OrderItemResponseDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.OrderResponseDTO;
import com.eubrunoo07.ecommerce.api.dtos.response.UserOrderResponseDTO;
import com.eubrunoo07.ecommerce.api.entities.Order;
import com.eubrunoo07.ecommerce.api.entities.OrderItem;
import com.eubrunoo07.ecommerce.api.entities.Product;
import com.eubrunoo07.ecommerce.api.entities.User;
import com.eubrunoo07.ecommerce.api.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/orders")
@CrossOrigin(originPatterns = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody@Valid OrderRequestDTO dto){
        // Chama o serviço para criar o pedido com base nos dados fornecidos no DTO
        Order order = orderService.createOrder(dto);

        // Retorna uma resposta indicando que o pedido foi criado com sucesso e inclui o ID do pedido
        return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrder(@PathVariable String id) {
        // Busca o pedido pelo ID
        Order order = orderService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        // Inicializa a lista de DTOs de itens do pedido
        List<OrderItemResponseDTO> orderItems = new ArrayList<>();

        // Itera sobre os itens do pedido para criar DTOs correspondentes
        for (OrderItem item : order.getOrders()) {
            Product product = item.getProduct();

            // Cria um DTO para o item do pedido com os detalhes do produto associado
            OrderItemResponseDTO itemDTO = OrderItemResponseDTO.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .build();

            // Adiciona o DTO do item à lista de DTOs de itens do pedido
            orderItems.add(itemDTO);
        }
        //Cria a instância de User
        User user = order.getUser();

        //Cria o DTO de acordo com as informações do Usuário
        UserOrderResponseDTO userDTO = UserOrderResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();

        //Por fim, retornando meu DTO de Pedido
        return OrderResponseDTO.builder()
                .id(order.getId())
                .status(order.getStatus().getDescription())
                .items(orderItems)
                .total(order.getTotal())
                .paymentType(order.getPaymentType().getDescription())
                .useCoupon(order.isUseCoupon())
                .user(userDTO)
                .datePlaced(order.getDatePlaced())
                .arrivalForecast(order.getArrivalForecast())
                .coupon(order.getCoupon())
                .build();

    }
}
