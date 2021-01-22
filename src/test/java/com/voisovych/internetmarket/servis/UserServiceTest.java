package com.voisovych.internetmarket.servis;

import com.voisovych.internetmarket.model.Role;
import com.voisovych.internetmarket.model.Status;
import com.voisovych.internetmarket.model.User;
import com.voisovych.internetmarket.repository.RoleRepository;
import com.voisovych.internetmarket.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameWithExceptionTest() {

        when(userRepository.findByUsername("Oleg")).thenReturn(null);
        userService.loadUserByUsername("Oleg");
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void loadUserByUsernameTest() {

        Status status = Status.ACTIVE;
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "Oleg",
                "oleg",
                true,
                true,
                true,
                true,
                Collections.singleton(new Role(1L, "ROLE_USER"))

        );

        User user = new User();
        user.setUsername("Oleg");
        user.setPassword("oleg");
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setStatus(status);

        when(userRepository.findByUsername("Oleg")).thenReturn(user);
        Assert.assertEquals(userDetails, userService.loadUserByUsername("Oleg"));
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void allUsersTest() {

        List<User> iterableOne = new LinkedList<>();
        User userOne = new User();
        userOne.setUsername("Oleg");
        iterableOne.add(userOne);

        List<User> iterableTwo = new LinkedList<>();
        User userTwo = new User();
        userTwo.setUsername("Oleg");
        iterableTwo.add(userOne);

        when(userRepository.findAll()).thenReturn(iterableTwo);
        Assert.assertEquals(iterableOne, userService.allUser());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void saveUserForExistingUserTest() {

        User user = new User();
        user.setUsername("Oleg");


        when(userRepository.findByUsername("Oleg")).thenReturn(user);
        Assert.assertFalse(userService.saveUser(user, "user"));
        verify(userRepository, never()).save(user);
    }

    @Test
    public void saveUserForNotExistingUserTest() {

        User userOne = new User();
        userOne.setUsername("Oleg");


        when(userRepository.findByUsername("Oleg")).thenReturn(null);
        Assert.assertTrue(userService.saveUser(userOne, "user"));
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void deleteUserSuccessful() {

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        Assert.assertTrue(userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void deleteUserNotSuccessful() {

        when(userRepository.findById(1L)).thenReturn(null);
        Assert.assertFalse(userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void saveEditUserTest() {

        User userOne = new User();
        userOne.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));

        User userTwo = new User();
        userTwo.setRoles(Collections.singleton(new Role(3L, "ROLE_SELLER")));
        Role one = null;
        Role two = null;

        when(userRepository.save(userOne)).thenReturn(userTwo);
        for (Role r: userService.saveEditUser(userOne, "ROLE_SELLER").getRoles()){
            one = r;
        }
        for (Role r: userOne.getRoles()){
            two = r;
        }

        Assert.assertEquals(one.getAuthority(), two.getAuthority());
        verify(userRepository, times(1)).save(any(User.class));
    }

}