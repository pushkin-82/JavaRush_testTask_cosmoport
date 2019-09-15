package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import com.space.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    public ShipRepository getShipRepository() {
        return shipRepository;
    }

    @Autowired
    private ShipRepository shipRepository;

    @Override
    @Transactional
    public List<Ship> getAllShips(Specification<Ship> specification) {
        return shipRepository.findAll(specification);
    }

    @Override
    @Transactional
    public Page<Ship> getAllShips(Specification<Ship> specification, Pageable pageable) {
        return shipRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public Ship save(Ship ship) {
        Double rating = Helper.calculateRating(ship.getProdDate(), ship.getSpeed(), ship.isUsed());
        ship.setRating(rating);

        return shipRepository.saveAndFlush(ship);
    }

    @Override
    @Transactional
    public Ship updateShip(Ship ship) {
        return save(ship);
    }

    @Override
    @Transactional
    public void deleteShipById(Long id) {
        shipRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Ship getShipById(Long id) {
        return shipRepository.findById(id).get();
    }

    @Override
    public boolean existById(Long id) {
        return shipRepository.existsById(id);
    }

//    @Override
//    public Long shipCount() {
//        return shipRepository.count();
//    }


}
