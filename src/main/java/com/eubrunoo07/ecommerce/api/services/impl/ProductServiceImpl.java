package com.eubrunoo07.ecommerce.api.services.impl;

import com.eubrunoo07.ecommerce.api.entities.Product;
import com.eubrunoo07.ecommerce.api.repositories.ProductRepository;
import com.eubrunoo07.ecommerce.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllExample(Example<Product> example) {
        return productRepository.findAll(example);
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }
}
