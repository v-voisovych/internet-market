package com.voisovych.internetmarket.controller;

import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.model.User;
import com.voisovych.internetmarket.servis.ItemCRUDService;
import com.voisovych.internetmarket.servis.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AdminController {

    Logger logger = LogManager.getLogger(AdminController.class);

    private ItemCRUDService itemCRUDService;
    private UserService userService;

    @Autowired
    public AdminController(ItemCRUDService itemCRUDService, UserService userService) {
        this.itemCRUDService = itemCRUDService;
        this.userService = userService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute Item item, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors() || item.getName().equals("") || item.getDescription().equals("")) {
            model.addAttribute("error", "Введено неправильні дані!!!!");
            return "redirect:/";
        }
        itemCRUDService.save(item);
        logger.info("Item {} was added by user with User Name {}", item.getName(), principal.getName());
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteItem(@RequestParam long id, Principal principal) {
        Item item = itemCRUDService.findById(id);
        if(itemCRUDService.delete(id)){
            logger.info("Item with name: {} deleted by user {}", item.getName(), principal.getName());
            return "redirect:/";
        }else {
            logger.warn("Item with id {} wasn't deleted",  id);
            return "redirect:/";
        }
    }

    @RequestMapping("/users")
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.allUser());
        model.addAttribute("user", new User());
        model.addAttribute("username", principal.getName());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User userForm, String role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allUsers", userService.allUser());
            return "users";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords don't much");
            model.addAttribute("allUsers", userService.allUser());
            return "users";
        }
        if (!userService.saveUser(userForm, role)) {
            model.addAttribute("usernameError", "User already exists");
            model.addAttribute("allUsers", userService.allUser());
            return "users";
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/deleteusers", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/saveEditUsers", method = RequestMethod.POST)
    public String saveEditUser(@ModelAttribute User user, String role) {
        userService.saveEditUser(user, role);
        return "redirect:/users";
    }

}
