package com.voisovych.internetmarket.controller;

import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.servis.ItemCRUDService;
import com.voisovych.internetmarket.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    ItemCRUDService itemCRUDService;

    @Autowired
    UserService userService;

    @RequestMapping("/itemform")
    public String showItemForm (Model model){
        model.addAttribute("command", new Item());
        return "itemform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveItem (@ModelAttribute Item item, BindingResult result, Model model){
        if (result.hasErrors() || item.getName().equals("") || item.getDescription().equals("")) {
            model.addAttribute("error", "Введено неправильні дані!!!!");
            return "erroradd";
        }
        itemCRUDService.save(item);
        return "redirect:/itemform";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteItem (@RequestParam long id){
        itemCRUDService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/users")
    public String showAllUsers(Model model){
        model.addAttribute("allUsers",userService.allUser());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model){
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/users";
    }
}
