package com.eubrunoo07.ecommerce.api.services;

import com.eubrunoo07.ecommerce.api.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(String id);
}
