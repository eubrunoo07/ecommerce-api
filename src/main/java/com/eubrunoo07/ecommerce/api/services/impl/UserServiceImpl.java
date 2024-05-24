package com.eubrunoo07.ecommerce.api.services.impl;

import com.eubrunoo07.ecommerce.api.entities.User;
import com.eubrunoo07.ecommerce.api.repositories.UserRepository;
import com.eubrunoo07.ecommerce.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
