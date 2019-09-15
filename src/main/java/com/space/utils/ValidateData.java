package com.space.utils;

import com.space.exceptions.CannotCreateShipException;
import com.space.exceptions.IncorrectDataException;
import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ValidateData {

    public static void validateId(Long id) {
        if (id < 1) {
            throw new IncorrectDataException();
        }
    }

    public static void validateCreateShipParams(Ship ship) {
        String name = ship.getName();
        String planet = ship.getPlanet();
        ShipType shipType = ship.getShipType();
        Date prodDate = ship.getProdDate();
        Double speed = ship.getSpeed();
        Integer crewSize = ship.getCrewSize();

        if (name == null || planet == null || shipType == null
                || prodDate == null || speed == null || crewSize == null) {
            throw new CannotCreateShipException("Some fields are null!");
        }

        int prodYear = getProdYear(ship);

        if (name.length() > 50 || StringUtils.isEmpty(name)) {
            throw new CannotCreateShipException("Invalid name");
        }
        if (planet.length() > 50 || StringUtils.isEmpty(planet)) {
            throw new CannotCreateShipException("Invalid planet");
        }
        if (crewSize < 1 || crewSize > 9999) {
            throw new CannotCreateShipException("Invalid crew size");
        }
        if (speed < 0.01 || speed > 0.99) {
            throw new CannotCreateShipException("Invalid speed data");
        }
        if (prodDate.getTime() < 0 || prodYear > Ship.CURRENT_YEAR || prodYear < Ship.INITIAL_YEAR) {
            throw new CannotCreateShipException("Invalid production date");
        }
    }

    public static void validateUpdateShipParams(Ship ship) {
        String name = ship.getName();
        String planet = ship.getPlanet();
        Date prodDate = ship.getProdDate();
        Double speed = ship.getSpeed();
        Integer crewSize = ship.getCrewSize();

        int prodYear = 0;

        if (prodDate != null) {
            prodYear = getProdYear(ship);
        }


        if (name != null && (name.length() > 50 || StringUtils.isEmpty(name))) {
            throw new CannotCreateShipException("Invalid name");
        }
        if (planet != null && (planet.length() > 50 || StringUtils.isEmpty(planet))) {
            throw new CannotCreateShipException("Invalid planet");
        }
        if (crewSize != null && (crewSize < 1 || crewSize > 9999)) {
            throw new CannotCreateShipException("Invalid crew size");
        }
        if (speed != null && (speed < 0.01 || speed > 0.99)) {
            throw new CannotCreateShipException("Invalid speed data");
        }
        if (prodDate != null && (prodDate.getTime() < 0 || prodYear > Ship.CURRENT_YEAR || prodYear < Ship.INITIAL_YEAR)) {
            throw new CannotCreateShipException("Invalid production date");
        }
    }

    private static int getProdYear(Ship ship) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(ship.getProdDate());
        return calendar.get(Calendar.YEAR);
    }

}
