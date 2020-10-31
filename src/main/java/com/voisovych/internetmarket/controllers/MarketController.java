package com.voisovych.internetmarket.controllers;

import com.voisovych.internetmarket.dao.ItemDAO;
import com.voisovych.internetmarket.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MarketController {

    @Autowired
    ItemDAO itemDAO;

    @RequestMapping("/")
    public String viewitem(Model model){
        List<Item> list = itemDAO.getItems();
        model.addAttribute("list", list);
        return "viewitem";
    }

    @RequestMapping("/itemform")
    public String showform(Model model){
        model.addAttribute("command", new Item());
        return "itemform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("item") Item item, BindingResult result, Model model){
        if (result.hasErrors() || item.getName().equals("") || item.getDescription().equals("")){
            model.addAttribute("error", "Введено неправильні дані!!!!");
            return "erroradd";
        }
        itemDAO.save(item);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        List<Item> list = itemDAO.getById(id);
        model.addAttribute("edit", list.get(0));
        return "editform";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("item") Item item, BindingResult result, Model model){
        if (result.hasErrors() || item.getName().equals("") || item.getDescription().equals("")){
            model.addAttribute("error", "Введено неправильні дані!!!!");
            int id = item.getId();
            model.addAttribute("id", id);
            return "erroredit";
        }
        itemDAO.update(item);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        itemDAO.delete(id);
        return "redirect:/";
    }
}
