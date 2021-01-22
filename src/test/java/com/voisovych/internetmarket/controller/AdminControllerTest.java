package com.voisovych.internetmarket.controller;

import com.voisovych.internetmarket.model.Role;
import com.voisovych.internetmarket.model.User;
import com.voisovych.internetmarket.repository.ItemCRUDRepository;
import com.voisovych.internetmarket.repository.UserRepository;
import com.voisovych.internetmarket.servis.ItemCRUDService;
import com.voisovych.internetmarket.servis.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql-query/item-query/create-item-before.sql", "/sql-query/user-query/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql-query/item-query/create-items-after.sql", "/sql-query/user-query/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ItemCRUDService itemCRUDService;

    @Mock
    private UserService userService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Principal principal;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BindingResult bindingResult;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @Test
    public void showAllUsersTest() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());
        when(userService.allUser()).thenReturn(new ArrayList<>());
        adminController.showAllUsers(model, principal);
        verify(userService, times(1)).allUser();
        verify(model, times(1)).addAttribute("allUsers", new ArrayList<>());
//        verify(model, times(1)).addAttribute("user", new User());
        verify(model, times(1)).addAttribute("username", principal.getName());
    }


//    dorobyty
    @Test
    public void addUserTest() throws Exception {
        User user = new User();

        when(userService.saveUser(user, "admin")).thenReturn(true);
        when(userService.allUser()).thenReturn(new ArrayList<>());
        user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));

        this.mockMvc.perform(post("/users")
                .param("id", "26")
                .param( "username", "ivan")
                .param("password","password")
                .param("passwordConfirm", "password")
                .param("status", "ACTIVE")
                .param("role", "admin")
                .flashAttr("user", user))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    public void addUserWithBadPasswordConfirmTest() throws Exception {
        User user = new User();

        when(userService.allUser()).thenReturn(new ArrayList<>());
        user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));

        this.mockMvc.perform(post("/users")
                .param("id", "26")
                .param("username", "ivan")
                .param("password", "password")
                .param("passwordConfirm", "passsword")
                .param("status", "ACTIVE")
                .param("role", "admin")
                .flashAttr("user", user))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());

        adminController.addUser(user, "admin", bindingResult, model);
        verify(model, times(1)).addAttribute("passwordError", "Passwords don't much");
        verify(model, times(1)).addAttribute("allUsers", new ArrayList<>());
        verify(userService, never()).saveUser(user, "admin");
    }

    @Test
    public void addUserWithErrorTest() {
        User user = new User();
        user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));

        when(userService.allUser()).thenReturn(new ArrayList<>());
        when(bindingResult.hasErrors()).thenReturn(true);
        user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));

        adminController.addUser(user, "admin", bindingResult, model);
        verify(model, times(1)).addAttribute("allUsers", new ArrayList<>());
        verify(userService, never()).saveUser(user, "admin");
    }

    @Test
    public void addExistingUserTest() throws Exception {
        User user = new User();

        when(userService.saveUser(user, "admin")).thenReturn(false);
        when(userService.allUser()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(post("/users")
                .param("id", "2")
                .param("username", "seller")
                .param("password", "seller")
                .param("passwordConfirm", "seller")
                .param("status", "ACTIVE")
                .param("role", "admin")
                .flashAttr("user", user))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk());

        adminController.addUser(user, "admin", bindingResult, model);
        verify(model, times(1)).addAttribute("usernameError", "User already exists");
        verify(model, times(1)).addAttribute("allUsers", userService.allUser());
        verify(userService, times(1)).saveUser(user, "admin");
    }

    @Test
    public void deleteUserTest() throws Exception {
        this.mockMvc.perform(post("/deleteusers")
                .param("userId", "2")
                .param("action","delete"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/users"))
                .andExpect(status().isFound());
        adminController.deleteUser(2l, "delete", model);
        verify(userService, times(1)).deleteUser(2l);
    }

    @Test
    public void deleteWithErrorTest() throws Exception {
        this.mockMvc.perform(post("/deleteusers")
                .param("userId", "2")
                .param("action","bbbbb"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/users"))
                .andExpect(status().isFound());
        adminController.deleteUser(2l, "bbbbbb", model);
        verify(userService, never()).deleteUser(2l);
    }

    @Test
    public void saveEditUserTest() throws Exception {
        User user = new User();
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_SELLER")));

        this.mockMvc.perform(post("/saveEditUsers")
                .param("id", "2")
                .param("username", "ivan")
                .param("password", "seller")
                .param("passwordConfirm", "seller")
                .param("status", "ACTIVE")
                .param("role", "admin")
                .flashAttr("user", user))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/users"))
                .andExpect(status().isFound());
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(post("/delete")
                .param("id", "2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }

}