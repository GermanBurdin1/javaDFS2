package model;

import java.util.UUID;

public class User {
    private final String id;
    private String firstName;

    public User(String firstName) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + "', firstName='" + firstName + "'}";
    }
}
