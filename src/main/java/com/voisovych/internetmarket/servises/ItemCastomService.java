package com.voisovych.internetmarket.servises;

import com.voisovych.internetmarket.models.Item;
import com.voisovych.internetmarket.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;

@Service
public class ItemCastomService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public List<Item> getByType(String type){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);

        Root<Item> itemRoot = criteriaQuery.from(Item.class);
        Predicate itemByType = criteriaBuilder.equal(itemRoot.get("type"), type);
        criteriaQuery.where(itemByType);

        TypedQuery<Item> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Item> getByName(String name){
        Query query = entityManager.createQuery("SELECT i FROM Item i WHERE i.name = :name");
        query.setParameter("name", name);
        List<Item> list = query.getResultList();
        return list;
    }
}
