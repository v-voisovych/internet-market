package com.voisovych.internetmarket.servis;

import com.voisovych.internetmarket.model.Role;
import com.voisovych.internetmarket.model.User;
import com.voisovych.internetmarket.repository.RoleRepository;
import com.voisovych.internetmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId){
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUser(){
        Iterable<User> iterable = userRepository.findAll();
        List<User> list = new LinkedList();
        for (User user: iterable){
            list.add(user);
        }
        return list;
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId){
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
