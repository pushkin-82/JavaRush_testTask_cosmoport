package com.space.utils;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class Specifications {

    private static Specification<Ship> filterByName(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Ship> filterByPlanet(String planet) {
        if (planet == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("planet"), "%" + planet + "%");
    }

    private static Specification<Ship> filterByShipType(ShipType shipType) {
        if (shipType == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("shipType"), shipType);
    }

    private static Specification<Ship> filterByProdDate(Long after, Long before) {
        if (after == null && before == null) {
            return null;
        } else if (after == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), new Date(before));
        } else if (before == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), new Date(after));
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("prodDate"), new Date(after), new Date(before));
        }
    }

    private static Specification<Ship> filterByUsage(Boolean isUsed) {
        if (isUsed == null) {
            return null;
        }

        return (root, query, criteriaBuilder) ->
                isUsed ? criteriaBuilder.isTrue(root.get("isUsed")) : criteriaBuilder.isFalse(root.get("isUsed"));
    }

    private static Specification<Ship> filterBySpeed(Double minSpeed, Double maxSpeed) {
        if (minSpeed == null && maxSpeed == null) {
            return null;
        } else if (minSpeed == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("speed"), maxSpeed);
        } else if (maxSpeed == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), minSpeed);
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("speed"), minSpeed, maxSpeed);
        }
    }

    private static Specification<Ship> filterByCrewSize(Integer minCrewSize, Integer maxCrewSize) {
        if (minCrewSize == null && maxCrewSize == null) {
            return null;
        } else if (minCrewSize == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), maxCrewSize);
        } else if (maxCrewSize == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), minCrewSize);
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("crewSize"), minCrewSize, maxCrewSize);
        }
    }

    private static Specification<Ship> filterByRating(Double minRating, Double maxRating) {
        if (minRating == null && maxRating == null) {
            return null;
        } else if (minRating == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("rating"), maxRating);
        } else if (maxRating == null) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating);
        } else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("rating"), minRating, maxRating);
        }
    }

    public static Specification<Ship> getAllShipsSpecification(
            String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
            Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize,
            Double minRating, Double maxRating
    ) {
        Specification<Ship> specification = Specification.where(filterByName(name)).and(filterByPlanet(planet)).
                and(filterByShipType(shipType)).and(filterByProdDate(after, before)).and(filterByUsage(isUsed)).
                and(filterBySpeed(minSpeed, maxSpeed)).and(filterByCrewSize(minCrewSize, maxCrewSize)).
                and(filterByRating(minRating, maxRating));

        return specification;
    }


}
