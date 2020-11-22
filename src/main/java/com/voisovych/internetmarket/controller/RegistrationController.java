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

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult result, Model model){
        if (result.hasErrors()){
            return "registration";
        }
        if(!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Passwords don't much");
            return "registration";
        }
        if(!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "User already exists");
            return "registration";
        }
        return "redirect:/";
    }

}
