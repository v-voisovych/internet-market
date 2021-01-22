package com.voisovych.internetmarket.servis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.voisovych.internetmarket.model.Bus;
import com.voisovych.internetmarket.repository.BusRepository;

@Service
public class BusService {

    private BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public void addBus(Bus bus) {
        busRepository.save(bus);
    }

    public Iterable<Bus> allBuses(){
        return busRepository.findAll();
    }

    public void delete (String routeNumber) {
        Bus bus = busRepository.findByRouteNumber(routeNumber);
        busRepository.delete(bus);
    }
}
