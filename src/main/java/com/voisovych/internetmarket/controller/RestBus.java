package com.voisovych.internetmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.voisovych.internetmarket.model.Bus;
import com.voisovych.internetmarket.servis.BusService;

@RestController
public class RestBus {

    @Autowired
    BusService busService;

    @RequestMapping(value = "/addBus", method = RequestMethod.POST)
    public ResponseEntity addBuss (@RequestBody Bus bus) {
        busService.addBus(bus);
        return new ResponseEntity("Bus added", HttpStatus.OK);
    }

    @RequestMapping(value = "/allBusses")
    public Iterable<Bus> allBusses() {
        return busService.allBuses();
    }

    @RequestMapping(value = "/deleteBus", method = RequestMethod.POST)
    public ResponseEntity deleteBus (@RequestParam String routeNumber) {
        busService.delete(routeNumber);
        return new ResponseEntity("Bus deleted", HttpStatus.OK);
    }
}
