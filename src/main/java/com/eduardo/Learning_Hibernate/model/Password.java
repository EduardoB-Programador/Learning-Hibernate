package com.eduardo.Learning_Hibernate.model;

import org.eduardo.mappifier.Mappifier;

import static com.eduardo.Learning_Hibernate.model.util.CheckingMethods.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public record Password(String hasspass) implements Mappifier {

    public Password(String hasspass) {
        isNull(hasspass);
        this.hasspass = hash(hasspass);
    }

    private static String hash(String value) {
        try {
            byte[] bytes = MessageDigest.getInstance("SHA-256").digest(value.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Internal Error");
        }
    }


    @Override
    public Object fromMap(Map<String, Object> map) {
        return null;
    }
}
