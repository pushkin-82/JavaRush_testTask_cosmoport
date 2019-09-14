package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    ShipRepository shipRepository;

    @Override
    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    @Override
    public Ship create() {
        Ship newShip = new Ship();
        newShip.setId(1L);
        newShip.setName("Pushkin");
        newShip.setPlanet("Uranus");
        newShip.setShipType(ShipType.MILITARY);
        newShip.setProdDate(new Date());
        newShip.setUsed(false);
        newShip.setSpeed(0.56);
        newShip.setCrewSize(4);
        newShip.setRating(30.0);

        return update(newShip.getId());
    }

    @Override
    public Ship update(Long id) {
        Ship updShip = shipRepository.getOne(id);
        return shipRepository.saveAndFlush(updShip);
    }

    @Override
    public void delete(Long id) {
        shipRepository.deleteById(id);
    }

    @Override
    public Ship getShipById(Long id) {
        return shipRepository.getOne(id);
    }

    @Override
    public List<Ship> getFilteredShipsList() {
        return null;
    }

    @Override
    public Long getFilteredShipsListCount() {
        return null;
    }
}
