package com.Pantheon.aries.Model;

import javax.persistence.*;
//
//@Entity
//@Table
public class EndUser {

//    @Id
//    @SequenceGenerator(
//            name = "ET_USERS",
//            sequenceName  = "ET_USERS_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.IDENTITY,
//            generator = "ET_USERS_sequence")
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public EndUser() {
    }

    public EndUser(Integer userId) {
        this.userId = userId;
    }

    public EndUser(Integer userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EndUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
