package com.voisovych.internetmarket.controller;


import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.servis.ItemCRUDService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import java.util.List;

@RestController
public class MyRestApi {

    private ItemCRUDService itemCRUDService;

    @Autowired
    public MyRestApi(ItemCRUDService itemCRUDService) {
        this.itemCRUDService = itemCRUDService;
    }

    Logger logger = LogManager.getLogger(MyRestApi.class);

    @RequestMapping(value = "/allItemRest")
    @ResponseBody
    public List<Item> allItemsRest() throws Exception {
        if (itemCRUDService.findAll().size() == 0){
            logger.error("Items don't exist");
            throw new Exception("No items");
        }
        logger.info("All items founded");
        return itemCRUDService.findAll();
    }

    @RequestMapping(value = "/findItemRest")
    @ResponseBody
    public Item findItemsRest(@RequestParam long id) throws Exception {
        if (itemCRUDService.findById(id) == null){
            logger.warn("Item with id: {} didn't found", id);
            throw new Exception("No item");
        }
        logger.info("Item with id: {} founded", id);
        return itemCRUDService.findById(id);
    }

    @RequestMapping(value = "/addItemRest", method = RequestMethod.POST)
    public ResponseEntity addItemRest(@RequestBody Item item){
        try {
            Item itemFounded = itemCRUDService.findById(item.getId());
            itemCRUDService.save(item);
            if (itemFounded != null){
                logger.info("Item with name: {} has edited", item.getName());
            }else {
                logger.info("Item with name: {} has added", item.getName());
            }
            return new ResponseEntity("Request was successful", HttpStatus.OK);
        }catch (Exception exception) {
            logger.error("Ups, Item hasn't added, Name can't be null");
            return new ResponseEntity("Request wasn't successful", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/deleteItemRest", method = RequestMethod.POST)
    public ResponseEntity deleteItemRest(@RequestParam long id){
        if(itemCRUDService.delete(id)){
            logger.info("Item with id: {} has deleted ", id);
            return new ResponseEntity("Deleting of item is successful", HttpStatus.OK);
        }else {
            logger.error("Item with id {} doesn't exist",  id);
            return new ResponseEntity("Item with id: " + id + " doesn't exist", HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/addListItemsRest", method = RequestMethod.POST)
    public ResponseEntity addListItemsRest(@RequestBody Iterable<Item> list) {
        try {
            itemCRUDService.saveAll(list);
            logger.info("All items added");
            return new ResponseEntity("is successful", HttpStatus.OK);
        }catch (Throwable exception) {
            logger.error("Ups, Item hasn't added, Name can't be null");
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}