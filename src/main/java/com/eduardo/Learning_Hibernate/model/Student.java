package com.eduardo.Learning_Hibernate.model;

import org.eduardo.mappifier.Mappifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public record Student(Email email, Password password, List<String> subjects, List<Professor> professors) implements Mappifier {

    public Student(Email email, Password password, List<String> subjects, List<Professor> professors) {
        this.email = email;
        this.password = password;
        this.subjects = Objects.requireNonNullElseGet(subjects, ArrayList::new);
        this.professors = Objects.requireNonNullElseGet(professors, ArrayList::new);
    }

    public Student(String email, String password, List<String> subjects, List<Professor> professors) { this(new Email(email), new Password(password), subjects, professors); }

    public Student(Email email, Password password) { this(email, password, null, null); }

    public Student(String email, String password) {this(new Email(email), new Password(password), null, null); }

    @SuppressWarnings("unchecked")
    @Override
    public Object fromMap(Map<String, Object> map) {
        boolean containsAll = map.containsKey("email") && map.containsKey("password") && map.containsKey("subjects") && map.containsKey("professors");

        if (containsAll) {
            Email email = (Email) map.get("email");
            Password password = (Password) map.get("password");
            List<String> subjects = (List<String>) map.get("subjects");
            List<Professor> professors = (List<Professor>) map.get("professors");

            return new Student(email, password, subjects, professors);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.toMap().toString();
    }
}
