package com.voisovych.internetmarket.servises;

import com.voisovych.internetmarket.models.Item;
import com.voisovych.internetmarket.repositories.ItemCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemCRUDService {

    @Autowired
    ItemCRUDRepository itemCRUDRepository;

    public List<Item> search(Item item) {

        List<Item> list = itemCRUDRepository.findAll();
        int notNull = 0;

        if (item.getCount() != null){
            notNull = notNull + 1;
        }
        if (item.getName() != null){
            notNull = notNull + 1;
        }
        if (item.getNumber() != null){
            notNull = notNull + 1;
        }
        if (item.getType() != null){
            notNull = notNull + 1;
        }
        if (item.getDescription() != null){
            notNull = notNull + 1;
        }
        if (item.getPrice() != null){
            notNull = notNull + 1;
        }
        if (item.getCreationDate() != null){
            notNull = notNull + 1;
        }

        List<Item> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int count = 0;
            Item listItem = list.get(i);
            if (listItem.getCount().equals(item.getCount())) {
                count = count + 1;
            }
            if (listItem.getType().equals(item.getType())) {
                count = count + 1;
            }
            if (listItem.getDescription().equals(item.getDescription())) {
                count = count + 1;
            }
            if (listItem.getName().equals(item.getName())) {
                count = count + 1;
            }
            if (listItem.getNumber().equals(item.getNumber())) {
                count = count + 1;
            }
            if (listItem.getPrice().equals(item.getPrice())) {
                count = count + 1;
            }
            if (listItem.getCreationDate().equals(item.getCreationDate())) {
                count = count + 1;
            }
            if (count == notNull) {
                long id = listItem.getId();
                result.add(itemCRUDRepository.findById(id));
            }
        }
        return result;
    }

//    public List<Item> searchByTypeName(String type, String name){
//        return itemCRUDRepository.findItemsByTypeAndName(type, name);
//    }
//
//    public List<Item> searchByDate(String date) throws ParseException {
//        Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        return itemCRUDRepository.findAllByCreationDateBeforeOrderByCreationDate(creationDate);
//    }
//
//    public List<Item> findBetween (String dateOne, String dateTwo) throws ParseException {
//        Date creationDateOne = new SimpleDateFormat("yyyy-MM-dd").parse(dateOne);
//        Date creationDateTwo = new SimpleDateFormat("yyyy-MM-dd").parse(dateTwo);
//        return itemCRUDRepository.findAllByCreationDateBetween(creationDateOne, creationDateTwo);
//    }
}

