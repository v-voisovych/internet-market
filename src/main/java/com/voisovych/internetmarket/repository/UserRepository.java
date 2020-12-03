package com.voisovych.internetmarket.repository;

import com.voisovych.internetmarket.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String userName);
    User findByPassword(String password);
}