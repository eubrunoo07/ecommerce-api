package com.eubrunoo07.ecommerce.api.services;

import com.eubrunoo07.ecommerce.api.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Optional<Category> findById(String id);
}
