package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> getAllShips();
    Ship create();
    Ship update(Long id);
    void delete(Long id);
    Ship getShipById(Long id);
    List<Ship> getFilteredShipsList();
    Long getFilteredShipsListCount();

}
