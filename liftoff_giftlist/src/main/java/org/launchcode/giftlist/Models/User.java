package org.launchcode.giftlist.Models;

import java.util.List;
import java.util.Objects;

public class User {


    private int userID;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List wishLists;
    private List groups;


    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(){
    }


    public int getUserID() {
        return userID;
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

    public List getWishLists() {
        return wishLists;
    }

    public void setWishLists(List wishLists) {
        this.wishLists = wishLists;
    }

    public List getGroups() {
        return groups;
    }

    public void setGroups(List groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserID() == user.getUserID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID());
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    //Waiting to add all additional features for the password once we have the correct Hashing in place

    public void findWishLists(){
        //find all wishlists created by THIS USER
    }

    public void findSupaSpecificWishList(){
        //find a list by the WishList ID
    }



}
