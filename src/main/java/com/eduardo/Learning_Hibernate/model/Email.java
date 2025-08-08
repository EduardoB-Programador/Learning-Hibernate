package com.eduardo.Learning_Hibernate.model;

import org.eduardo.mappifier.Mappifier;

import static com.eduardo.Learning_Hibernate.model.util.CheckingMethods.*;

import java.util.Map;

public record Email(String email) implements Mappifier {

    public Email {
        isNull(email);
        isValidEmail(email);
    }

    private void isValidEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            throw new RuntimeException("Invalid email.");
    }

    @Override
    public String toString() {
        return this.email;
    }

    @Override
    public Object fromMap(Map<String, Object> map) {
        if (map.containsKey("email"))
            return new Email((String) map.get("email"));

        else
            return null;
    }
}