package com.voisovych.internetmarket.controllers;

import com.voisovych.internetmarket.dao.entity.Item;
import com.voisovych.internetmarket.servises.ItemCastomService;
import com.voisovych.internetmarket.servises.ItemStandartServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MarketController {

    @Autowired
    ItemStandartServise itemStandartServise;

    @Autowired
    ItemCastomService itemCastomService;

    @RequestMapping("/")
    public String viewitem (Model model){
        List<Item> list = itemStandartServise.list();
        for (int i = 0; i < list.size(); i++) {
            Item listItem = list.get(i);
            if (listItem.getType().equals("pc")) {
                listItem.setType("Комп'ютер");
            } else if (listItem.getType().equals("laptop")) {
                listItem.setType("Ноутбуки");
            } else if (listItem.getType().equals("phone")) {
                listItem.setType("Телефони");
            } else {
                listItem.setType("Консолі");
            }
        }
        model.addAttribute("list", list);
        return "allitems";
    }

    @RequestMapping("/type")
    public String viewItemByType (@RequestParam String type, Model model){
        List<Item> list = itemCastomService.getByType(type);
        if (list.isEmpty()) {
            model.addAttribute("nothing", ": відсутні");
        }
        model.addAttribute("list", list);
        if (type.equals("pc")) {
            model.addAttribute("type", "Комп'ютери");
        } else if (type.equals("laptop")) {
            model.addAttribute("type", "Ноутбуки");
        } else if (type.equals("phone")) {
            model.addAttribute("type", "Телефони");
        } else {
            model.addAttribute("type", "Консолі");
        }
        return "viewitem";
    }

    @RequestMapping("/itemform")
    public String showform (Model model){
        model.addAttribute("command", new Item());
        return "itemform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save (@ModelAttribute Item item, BindingResult result, Model model){
        if (result.hasErrors() || item.getName().equals("") || item.getDescription().equals("")) {
            model.addAttribute("error", "Введено неправильні дані!!!!");
            return "erroradd";
        }
        itemStandartServise.itemSave(item);
        return "redirect:/itemform";
    }

    @RequestMapping(value = "/edit")
    public String edit ( @RequestParam long id, Model model){
        List<Item> list = itemStandartServise.getById(id);
        model.addAttribute("edit", list.get(0));
        return "editform";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave (@ModelAttribute Item item, BindingResult result, Model model){
        if (result.hasErrors() || item.getName().equals("") || item.getDescription().equals("")) {
            model.addAttribute("error", "Введено неправильні дані!!!!");
            long id = item.getId();
            model.addAttribute("id", id);
            return "erroredit";
        }
        itemStandartServise.itemSave(item);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete (@RequestParam long id){
        itemStandartServise.delete(id);
        return "redirect:/";
    }
}
