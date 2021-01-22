package com.voisovych.internetmarket.servis;

import com.voisovych.internetmarket.model.Role;
import com.voisovych.internetmarket.model.Status;
import com.voisovych.internetmarket.model.User;
import com.voisovych.internetmarket.repository.RoleRepository;
import com.voisovych.internetmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user.fromUser(user);
    }

    public Iterable<User> allUser(){
        return userRepository.findAll();
    }

    public boolean saveUser(User user, String role){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB !=null){
            return false;
        }
        if (role.equals("seller")){
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_SELLER")));
        }else {
            if (role.equals("admin")) {
                user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));
            } else {
                user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            }
        }
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId){
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        throw new NullPointerException("Not found with id: " + userId);
    }

    public User saveEditUser(User user, String role) {
        if (role.equals("ROLE_SELLER")) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_SELLER")));
        } else {
            if (role.equals("ROLE_ADMIN")) {
                user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));
            } else {
                user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            }
        }
        return userRepository.save(user);
    }
}
