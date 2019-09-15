package com.space.controller;

import com.space.exceptions.ShipNotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import com.space.utils.Specifications;
import com.space.utils.ValidateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
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

    @GetMapping(path = "/ships/count")
    @ResponseStatus(HttpStatus.OK)
    public long getShipsCount(
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
            @RequestParam( value = "maxRating", required = false) Double maxRating
    ) {
        Specification<Ship> specification = Specifications.getAllShipsSpecification(name, planet, shipType,
                after, before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);

        return service.getAllShips(specification).size();
    }


    @PostMapping(path = "/ships")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Ship createShip(@RequestBody Ship newShip) {
        ValidateData.validateCreateShipParams(newShip);

        String name = newShip.getName();
        String planet = newShip.getPlanet();
        ShipType shipType = newShip.getShipType();
        Date prodDate = newShip.getProdDate();
        Boolean isUsed = newShip.isUsed();
        Double speed = newShip.getSpeed();
        Integer crewSize = newShip.getCrewSize();

        Ship ship;

        if (isUsed != null) {
            ship = new Ship(name, planet, shipType, prodDate, isUsed, speed, crewSize);
        } else {
            ship = new Ship(name, planet, shipType, prodDate, speed, crewSize);
        }

        service.save(ship);

        return ship;
    }


    @GetMapping(path = "/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship getShip(@PathVariable long id) {
        ValidateData.validateId(id);
        if (!service.existById(id)) {
            throw new ShipNotFoundException();
        }
        return service.getShipById(id);
    }

    @DeleteMapping(path = "/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        ValidateData.validateId(id);
        if (!service.existById(id)) {
            throw new ShipNotFoundException();
        }
        service.deleteShipById(id);
    }

    @PostMapping(path = "/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Ship update(@PathVariable long id, @RequestBody Ship ship) {
        ValidateData.validateId(id);
        if (!service.existById(id)) {
            throw new ShipNotFoundException();
        }

        ValidateData.validateUpdateShipParams(ship);

        Ship updatedShip = service.getShipById(id);

        if (ship.getName() != null) {
            updatedShip.setName(ship.getName());
        }
        if (ship.getPlanet() != null) {
            updatedShip.setPlanet(ship.getPlanet());
        }
        if (ship.getShipType() != null) {
            updatedShip.setShipType(ship.getShipType());
        }
        if (ship.getProdDate() != null) {
            updatedShip.setProdDate(ship.getProdDate());
        }
        if (ship.isUsed() != null) {
            updatedShip.setUsed(ship.isUsed());
        }
        if (ship.getSpeed() != null) {
            updatedShip.setSpeed(ship.getSpeed());
        }
        if (ship.getCrewSize() != null) {
            updatedShip.setCrewSize(ship.getCrewSize());
        }

        service.save(updatedShip);

        return updatedShip;
    }



}
