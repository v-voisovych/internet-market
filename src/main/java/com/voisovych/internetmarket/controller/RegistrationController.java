package com.voisovych.internetmarket.controller;

import com.voisovych.internetmarket.model.User;
import com.voisovych.internetmarket.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Controller
public class RegistrationController {
    Logger logger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("userForm", new User());
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, String role, BindingResult result, Model model){
        if (result.hasErrors()){
            return "login";
        }
        if(!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Passwords don't much");
            return "login";
        }
        if(!userService.saveUser(userForm, role)){
            model.addAttribute("usernameError", "User already exists");
            return "login";
        }
        return "redirect:/";
    }
}
