package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/rest")
public class ShipController {

    @Autowired
    private ShipService service;

    @RequestMapping(path = "/ships", method = RequestMethod.POST)
    @ResponseBody
    public Ship createShip() {
        return service.create();
    }

    @RequestMapping(path = "/ships", method = RequestMethod.GET)
    @ResponseBody
    public List<Ship> getAllShips() {
        return service.getAllShips();
    }

    @RequestMapping(path = "/ships/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Ship getShip(@PathVariable long id) {
        return service.getShipById(id);
    }

    @RequestMapping(path = "/ships/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/ships/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Ship update(@PathVariable long id) {
        return service.update(id);
    }

    @RequestMapping(path = "/ships/count", method = RequestMethod.GET)
    @ResponseBody
    public long getShipsCount() {
        return getAllShips().size();
    }


}
