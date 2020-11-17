package com.voisovych.internetmarket.repositories;

import com.voisovych.internetmarket.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemCRUDRepository extends CrudRepository<Item,Long> {

    List<Item> findAll();
    Item findById(long id);

    List<Item> findItemsByTypeAndName(String type, String name);

    List<Item> findAllByCreationDateBeforeOrderByCreationDate(Date creationDate);

    List<Item> findAllByCreationDateBetween(Date creationDateOne, Date creationDateTwo);
}
