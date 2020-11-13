package com.voisovych.internetmarket.repositories;

import com.voisovych.internetmarket.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
