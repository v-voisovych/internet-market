package com.voisovych.internetmarket.controllers;

import com.voisovych.internetmarket.models.Item;
import com.voisovych.internetmarket.servises.ItemCRUDService;
import com.voisovych.internetmarket.servises.ItemCastomService;
import com.voisovych.internetmarket.servises.ItemStandartServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class MarketController {

    @Autowired
    ItemStandartServise itemStandartServise;

    @Autowired
    ItemCastomService itemCastomService;

    @Autowired
    ItemCRUDService itemCRUDService;

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
    public String editSave (@ModelAttribute Item item, BindingResult result, String creationDate, Model model) throws ParseException {
        if (result.hasErrors()) {
            Date creationDateP = new SimpleDateFormat("yyyy-MM-dd").parse(creationDate);
            item.setCreationDate(creationDateP);
        }
//            model.addAttribute("error", "Введено неправильні дані!!!!");
            long id = item.getId();
            model.addAttribute("id", id);
//            return "erroredit";

        itemStandartServise.itemSave(item);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete (@RequestParam long id){
        itemStandartServise.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search (@ModelAttribute Item item, BindingResult result, String creationDate,String count, String price, String number, Model model) throws ParseException {
        if (result.hasErrors()) {
            if (creationDate.equals("")) {
                item.setCreationDate(null);
            }else {
                Date creationDateP = new SimpleDateFormat("yyyy-MM-dd").parse(creationDate);
                item.setCreationDate(creationDateP);
            }
            if (count.equals("")) {
                item.setCount(null);
            }
            if (price.equals("")) {
                item.setPrice(null);
            }
            if (number.equals("")) {
                item.setNumber(null);
            }
        }
        if (item.getName().equals("")){
            item.setName(null);
        }
        if (item.getType().equals("")){
            item.setType(null);
        }
        if (item.getDescription().equals("")){
            item.setDescription(null);
        }
        model.addAttribute("list", itemCRUDService.search(item));
        return "allitems";
    }

//    @RequestMapping("/typeandname")
//    public String search (@RequestParam String type, @RequestParam String name,Model model) {
//        model.addAttribute("list", itemCRUDService.searchByTypeName(type, name));
//        return "allitems";
//    }

//    @RequestMapping("/searchbydate")
//    public String searchDate (@RequestParam String creationDate, Model model) throws ParseException {
//        model.addAttribute("list", itemCRUDService.searchByDate(creationDate));
//        return "allitems";
//    }

//    @RequestMapping("/date")
//    public String searchByDate (@RequestParam String creationDateOne, @RequestParam String creationDateTwo, Model model) throws ParseException {
//        model.addAttribute("list", itemCRUDService.findBetween(creationDateOne, creationDateTwo));
//        return "allitems";
//    }
}
