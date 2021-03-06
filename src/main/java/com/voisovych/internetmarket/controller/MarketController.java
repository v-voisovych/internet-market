package com.voisovych.internetmarket.controller;

import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.servis.ItemCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MarketController {

    @Autowired
    ItemCRUDService itemCRUDService;

    @RequestMapping("/")
    public String viewItem (Model model, Principal principal){
        List<Item> list = itemCRUDService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("username", principal.getName());
        return "allitems";
    }

    @RequestMapping("/type")
    public String viewItemByType (@RequestParam String type, Model model, Principal principal){
        List<Item> list = itemCRUDService.findByType(type);
        model.addAttribute("list", list);
        model.addAttribute("username", principal.getName());
        return "allitems";
    }

    @RequestMapping(value = "/edit")
    public String edit (@RequestParam long id, Model model, Principal principal){
        model.addAttribute("edit", itemCRUDService.findById(id));
        model.addAttribute("username", principal.getName());
        return "editform";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave (@ModelAttribute Item item, BindingResult result, String creationDate, Model model) throws ParseException {
        Date creationDateP = new SimpleDateFormat("yyyy-MM-dd").parse(creationDate);
        item.setCreationDate(creationDateP);
        long id = item.getId();
        model.addAttribute("id", id);
        itemCRUDService.save(item);;
        return "redirect:/";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search (@ModelAttribute Item item, BindingResult result,
                          @RequestParam String creationDate,
                          @RequestParam String count,
                          @RequestParam String price,
                          @RequestParam String number,
                          Principal principal,
                          Model model) throws ParseException {

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
        model.addAttribute("username", principal.getName());
        return "allitems";
    }

}
