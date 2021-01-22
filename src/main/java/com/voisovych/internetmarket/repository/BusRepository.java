package com.voisovych.internetmarket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.voisovych.internetmarket.model.Bus;

@Repository
public interface BusRepository extends CrudRepository<Bus, Long> {

    Bus findByRouteNumber (String routeNumber);
}
