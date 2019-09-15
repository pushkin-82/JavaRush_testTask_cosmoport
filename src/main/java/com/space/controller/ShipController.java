package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import com.space.service.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/rest")
public class ShipController {

    @Autowired
    private ShipService service;

    @GetMapping(path = "/ships")
    @ResponseStatus(HttpStatus.OK)
    public List<Ship> getAllShips(
            @RequestParam( value = "name", required = false) String name,
            @RequestParam( value = "planet", required = false) String planet,
            @RequestParam( value = "shipType", required = false) ShipType shipType,
            @RequestParam( value = "after", required = false) Long after,
            @RequestParam( value = "before", required = false) Long before,
            @RequestParam( value = "isUsed", required = false) Boolean isUsed,
            @RequestParam( value = "minSpeed", required = false) Double minSpeed,
            @RequestParam( value = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam( value = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam( value = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam( value = "minRating", required = false) Double minRating,
            @RequestParam( value = "maxRating", required = false) Double maxRating,
            @RequestParam( value = "order", required = false, defaultValue = "ID") ShipOrder order,
            @RequestParam( value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam( value = "pageSize", required = false, defaultValue = "3") Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
        Specification<Ship> specification = Specifications.getAllShipsSpecification(name, planet, shipType,
                after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);

        return service.getAllShips(specification, pageable).getContent();
    }

    @RequestMapping(path = "/ships", method = RequestMethod.POST)
    @ResponseBody
    public Ship createShip() {
//        return service.create();
        return null;
    }


    @RequestMapping(path = "/ships/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Ship getShip(@PathVariable long id) {
        return service.getShipById(id);
    }

    @RequestMapping(path = "/ships/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.deleteShipById(id);
    }

    @RequestMapping(path = "/ships/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Ship update(@PathVariable long id) {
//        return service.update(id);
        return null;
    }

    @RequestMapping(path = "/ships/count", method = RequestMethod.GET)
    @ResponseBody
    public long getShipsCount() {
        return service.shipCount();
    }


}
