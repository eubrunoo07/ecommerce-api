package com.eubrunoo07.ecommerce.api.repositories;

import com.eubrunoo07.ecommerce.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
