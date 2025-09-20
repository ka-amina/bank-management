package org.bankManagment.Domain;

import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String password;

    public User(String fullName, String email, String password) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFullName(String userName) {
        this.fullName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
