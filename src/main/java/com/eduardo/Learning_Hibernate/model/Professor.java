package com.eduardo.Learning_Hibernate.model;

import org.eduardo.mappifier.Mappifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public record Professor(Email email, Password password, List<Student> students, String subject) implements Mappifier {

    public Professor(Email email, Password password, List<Student> students, String subject) {
        this.email = email;
        this.password = password;
        this.students = Objects.requireNonNullElseGet(students, ArrayList::new);
        this.subject = subject;
    }

    public Professor(String email, String password, List<Student> students, String subject) { this(new Email(email), new Password(password), students, subject); }

    public Professor(Email email, Password password) { this(email, password, null, null); }

    public Professor(String email, String password) { this(new Email(email), new Password(password), null, null); }

    @SuppressWarnings("unchecked")
    @Override
    public Object fromMap(Map<String, Object> map) {
        boolean containsAll = map.containsKey("email") && map.containsKey("password") && map.containsKey("students") && map.containsKey("subject");

        if (containsAll) {
            Email email = (Email) map.get("email");
            Password password = (Password) map.get("password");
            List<Student> students = (List<Student>) map.get("students");
            String subject = (String) map.get("subject");

            return new Professor(email, password, students, subject);
        }
        return null;
    }
}
