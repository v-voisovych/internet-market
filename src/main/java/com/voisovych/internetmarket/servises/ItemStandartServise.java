package com.voisovych.internetmarket.servises;

import com.voisovych.internetmarket.dao.entity.Item;
import com.voisovych.internetmarket.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemStandartServise {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> list() {
        return itemRepository.findAll();
    }

    public void itemSave(Item item) {
        itemRepository.save(item);
    }

    public List<Item> getById(long id){
        Optional<Item> item = itemRepository.findById(id);
        List<Item> res = new ArrayList<>();
        item.ifPresent(res::add);return res;
    }

    public void delete(long id){
        itemRepository.deleteById(id);
    }
}
