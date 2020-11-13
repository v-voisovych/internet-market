package com.voisovych.internetmarket.servises;

import com.voisovych.internetmarket.models.Item;
import com.voisovych.internetmarket.repositories.ItemCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ItemCRUDService {

    @Autowired
    ItemCRUDRepository itemCRUDRepository;

    public List<Item> searchByName(String name){
        return  itemCRUDRepository.findAllByName(name);
    }

    public List<Item> searchByTypeName(String type, String name){
        return itemCRUDRepository.findItemsByTypeAndName(type, name);
    }

//    public List<Item> searchByDate(String date) throws ParseException {
//        Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        return itemCRUDRepository.findAllByCreationDateAfterOrderByCreationDate(creationDate);
//    }

    public List<Item> findBetween (String dateOne, String dateTwo) throws ParseException {
        Date creationDateOne = new SimpleDateFormat("yyyy-MM-dd").parse(dateOne);
        Date creationDateTwo = new SimpleDateFormat("yyyy-MM-dd").parse(dateTwo);
        return itemCRUDRepository.findAllByCreationDateBetween(creationDateOne, creationDateTwo);
    }


}

