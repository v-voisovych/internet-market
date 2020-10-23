package com.voisovych.internetmarket.controllers;

import com.voisovych.internetmarket.dao.ItemDAO;
import com.voisovych.internetmarket.items.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MarketController {

    @Autowired
    ItemDAO itemDAO;

    @RequestMapping("/itemform")
    public String showform(Model model){
        model.addAttribute("command", new Item());
        return "itemform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("item") Item item){
        itemDAO.save(item);
        return "redirect:/";
    }

    @RequestMapping("/")
    public String viewitem(Model model){
        List<Item> list = itemDAO.getEmployees();
        model.addAttribute("list", list);
        return "viewitem";
    }


    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        List<Item> list = itemDAO.getById(id);
        model.addAttribute("edit", list.get(0));
        return "editform";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("item") Item item){
        itemDAO.update(item);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        itemDAO.delete(id);
        return "redirect:/";
    }
}
