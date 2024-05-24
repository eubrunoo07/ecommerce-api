package com.eubrunoo07.ecommerce.api.services;

import com.eubrunoo07.ecommerce.api.entities.Product;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    List<Product> findAll();
    List<Product> findAllExample(Example<Product> example);

    Optional<Product> findById(String id);
}
