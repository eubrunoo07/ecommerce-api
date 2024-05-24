package com.eubrunoo07.ecommerce.api.services.impl;

import com.eubrunoo07.ecommerce.api.dtos.request.OrderRequestDTO;
import com.eubrunoo07.ecommerce.api.dtos.request.ProductOrderDTO;
import com.eubrunoo07.ecommerce.api.entities.*;
import com.eubrunoo07.ecommerce.api.enums.DiscountType;
import com.eubrunoo07.ecommerce.api.enums.OrderStatus;
import com.eubrunoo07.ecommerce.api.enums.PaymentType;
import com.eubrunoo07.ecommerce.api.repositories.DiscountCouponRepository;
import com.eubrunoo07.ecommerce.api.repositories.OrderRepository;
import com.eubrunoo07.ecommerce.api.repositories.ProductRepository;
import com.eubrunoo07.ecommerce.api.repositories.UserRepository;
import com.eubrunoo07.ecommerce.api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DiscountCouponRepository couponRepository;

    @Override
    public Order createOrder(OrderRequestDTO dto) {
        // Busca o usuário pelo ID fornecido no DTO
        User user = userRepository.findById(dto.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Cria um novo pedido
        Order order = new Order();

        // Define o usuário associado ao pedido
        order.setUser(user);

        // Define o status do pedido como "Placed" (Realizado)
        order.setStatus(OrderStatus.AWAITING_PAYMENT);
        order.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));
        // Inicializa o total do pedido como zero
        BigDecimal total = BigDecimal.ZERO;

        // Inicializa uma lista para armazenar os itens do pedido
        List<OrderItem> orderItems = new ArrayList<>();

        // Itera sobre os produtos fornecidos no DTO para criar os itens do pedido
        for (ProductOrderDTO productOrderDTO : dto.getProducts()) {
            // Busca o produto pelo ID fornecido no DTO
            Product product = productRepository.findById(productOrderDTO.getProduct())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            if(product.getQuantityInStock() < 1){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No have products");
            }
            product.setQuantityInStock(product.getQuantityInStock() - 1);
            productRepository.save(product);

            // Cria um novo item de pedido
            OrderItem orderItem = new OrderItem();

            // Define o pedido associado ao item
            orderItem.setOrder(order);

            // Define o produto associado ao item
            orderItem.setProduct(product);

            // Define a quantidade do produto no item
            orderItem.setQuantity(productOrderDTO.getQuantity());

            // Adiciona o item à lista de itens do pedido
            orderItems.add(orderItem);

            // Calcula o subtotal do item e adiciona ao total do pedido
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(productOrderDTO.getQuantity())));
        }

        // Define a lista de itens do pedido
        order.setOrders(orderItems);

        //Setando se o usuario quer ou não cupom
        order.setUseCoupon(dto.isUseCoupon());

        // Define o total do pedido
        if(order.isUseCoupon()){
            String coupon = dto.getCoupon();
            DiscountCoupon discountCoupon = couponRepository
                    .findByCode(coupon).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coupon not found"));
            System.out.println(discountCoupon);
            BigDecimal discountAmount = BigDecimal.ZERO;
            if(discountCoupon != null){
                if(discountCoupon.getDiscountType() == DiscountType.PERCENTAGE){
                    discountAmount = total.multiply(discountCoupon.getDiscountValue().divide(BigDecimal.valueOf(100)));
                }
                else{
                    discountAmount = discountCoupon.getDiscountValue();
                }
            }
            order.setTotal(total.subtract(discountAmount));
        }
        else{
            order.setTotal(total);
        }

        //Define a previsão de chegada
        order.setArrivalForecast(LocalDateTime.now().plusDays(15));

        //Setando o cupom que o usuario vai usar
        if(!order.isUseCoupon() && dto.getCoupon() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You select 'no coupon'");
        }
        else{
            order.setCoupon(dto.getCoupon());
        }

        //Adiciona este pedido para a lista de pedidos do usuario
        user.getOrders().add(order);

        //Salva o usuario com este pedido
        userRepository.save(user);

        // Salva o pedido no banco de dados e retorna o objeto salvo
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
