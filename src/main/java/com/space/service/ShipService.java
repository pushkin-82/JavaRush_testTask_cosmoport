package com.space.service;

import com.space.model.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ShipService {
    List<Ship> getAllShips(Specification<Ship> specification);
    Page<Ship> getAllShips(Specification<Ship> specification, Pageable pageable);
    Ship save(Ship ship);
    Ship updateShip(Ship ship);
    void deleteShipById(Long id);
    Ship getShipById(Long id);
    Long shipCount();
}
