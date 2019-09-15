package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    ShipRepository shipRepository;

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
        Double rating = calculateRating(ship.getProdDate(), ship.getSpeed(), ship.isUsed());
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
    public Long shipCount() {
        return shipRepository.count();
    }

    private Double calculateRating(Date prodDate, Double speed, Boolean isUsed) {
        double coef = !isUsed ? 1.0 : 0.5;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(prodDate);
        int prodYear = calendar.get(Calendar.YEAR);
        double rating = 80 * speed * coef / (Ship.CURRENT_YEAR - prodYear + 1);

        BigDecimal roundRating = new BigDecimal(rating).setScale(2, RoundingMode.HALF_UP);

        return roundRating.doubleValue();
    }
}
