package oop.project.onlineshop.entities.impl;

import oop.project.onlineshop.entities.User;

public class DefaultUser implements User {
    private static int defaultUserCount = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    {
        ++defaultUserCount;
    }

    public DefaultUser() {
    }

    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.id = defaultUserCount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "DefaultUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    @Override
    public int getId() {
        return id;
    }

    void clearState() {
        defaultUserCount = 0;
    }
}
