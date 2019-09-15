package com.space.utils;

import com.space.model.Ship;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Helper {

    public static Double calculateRating(Date prodDate, Double speed, Boolean isUsed) {
        double coef = !isUsed ? 1.0 : 0.5;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(prodDate);
        int prodYear = calendar.get(Calendar.YEAR);
        double rating = 80 * speed * coef / (Ship.CURRENT_YEAR - prodYear + 1);

        BigDecimal roundRating = new BigDecimal(rating).setScale(2, RoundingMode.HALF_UP);

        return roundRating.doubleValue();
    }
}
