package com.eubrunoo07.ecommerce.api.repositories;

import com.eubrunoo07.ecommerce.api.entities.Order;
import com.eubrunoo07.ecommerce.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
}
