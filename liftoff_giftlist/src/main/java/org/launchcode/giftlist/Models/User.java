package org.launchcode.giftlist.Models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userID;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String pwHash;
    private List wishLists;
    private List groups;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public User(String firstName, String lastName, String username, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

    public User(){
    }

    public User(String username, String password) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", wishLists=" + wishLists +
                ", groups=" + groups +
                '}';
    }


    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    //Waiting to add all additional features for the password once we have the correct Hashing in place

    public void findWishLists(){
        //find all wishlists created by THIS USER
    }

    public void findSupaSpecificWishList(){
        //find a list by the WishList ID
    }



}
