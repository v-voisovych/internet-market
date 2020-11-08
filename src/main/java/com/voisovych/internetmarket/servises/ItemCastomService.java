package com.voisovych.internetmarket.servises;

import com.voisovych.internetmarket.dao.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ItemCastomService {

    @Autowired
    EntityManager entityManager;

    public List<Item> getByType(String type){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);

        Root<Item> itemRoot = criteriaQuery.from(Item.class);
        Predicate itemByType = criteriaBuilder.equal(itemRoot.get("type"), type);
        criteriaQuery.where(itemByType);

        TypedQuery<Item> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
