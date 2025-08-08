package com.eduardo.Learning_Hibernate.model.util;

public class CheckingMethods {

    public static void isNull(Object o) {
        if (o == null)
            throw new RuntimeException("Value " + o + "is null");
    }
}
