package com.space.utils;

import com.space.exceptions.IncorrectDataException;

public class ValidateData {

    public static void validateId(Long id) {
        if (id < 1) {
            throw new IncorrectDataException();
        }
    }
}
