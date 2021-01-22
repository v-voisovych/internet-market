package com.voisovych.internetmarket.servis;

import com.voisovych.internetmarket.model.Item;
import com.voisovych.internetmarket.repository.ItemCRUDRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemCRUDService {

    private ItemCRUDRepository itemCRUDRepository;

    @Autowired
    public ItemCRUDService(ItemCRUDRepository itemCRUDRepository) {
        this.itemCRUDRepository = itemCRUDRepository;
    }

    Logger logger = LogManager.getLogger(ItemCRUDService.class);

    public List<Item> search(Item item) {
        return itemCRUDRepository.findAllByCountAndNameAndDescriptionAndNumberAndPriceAndTypeAndCreationDate (
                item.getCount(), item.getName(), item.getDescription(), item.getNumber(), item.getPrice(), item.getType(), item.getCreationDate()
        );
    }

    public List<Item> findByType(String type){
       return itemCRUDRepository.findAllByType(type);
    }

    public List<Item> findAll(){
        return itemCRUDRepository.findAll();
    }


    public Item save(Item item) {

        return itemCRUDRepository.save(item);
    }

    public Item findById(Long id){
        return itemCRUDRepository.findAllById(id);
    }

    public boolean delete (Long id){
        if(itemCRUDRepository.findById(id).isPresent()) {
            itemCRUDRepository.deleteById(id);
            return true;
        }
        throw new EntityExistsException("Not found");
    }

    @Transactional
    public void saveAll(Iterable<Item> iterable) {
        itemCRUDRepository.saveAll(iterable);
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

