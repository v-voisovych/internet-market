package com.voisovych.internetmarket.repository;

import com.voisovych.internetmarket.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemCRUDRepository extends CrudRepository<Item,Long> {

    List<Item> findAll();

    List<Item> findAllByType(String type);

    Item findAllById(Long id);

    @Query("SELECT c FROM Item c WHERE (:count is null or c.count = :count) and (:name is null or c.name = :name)" +
            "and (:description is null or c.description = :description) and (:number is null or c.number = :number)" + "" +
            "and (:price is null or c.price = :price) and (:type is null or c.type = :type) and (:creationDate is null or c.creationDate = :creationDate)")
    List<Item> findAllByCountAndNameAndDescriptionAndNumberAndPriceAndTypeAndCreationDate(@Param("count") Integer count,
                                                                                          @Param("name") String name,
                                                                                          @Param("description") String description,
                                                                                          @Param("number") Integer number,
                                                                                          @Param("price") Float price,
                                                                                          @Param("type") String type,
                                                                                          @Param("creationDate") Date creationDate);
}
