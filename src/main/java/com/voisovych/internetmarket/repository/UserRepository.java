package com.voisovych.internetmarket.repository;

import com.voisovych.internetmarket.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String userName);
}