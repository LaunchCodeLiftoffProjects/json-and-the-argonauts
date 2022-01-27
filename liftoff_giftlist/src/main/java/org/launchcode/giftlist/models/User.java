package org.launchcode.giftlist.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends AbstractSuper {

    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotBlank
    @NotNull
    private String username;
    @NotNull
    @NotBlank
    private String email;
    private String pwHash;

    @OneToMany(mappedBy = "listOwner")
    private List<WishList> wishLists = new ArrayList<>();

    @OneToMany(mappedBy = "partyOwner")
    private List<Party> ownedParties = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private List<Party> joinedParties = new ArrayList<>();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


//  Constructors
    public User(String firstName, String lastName, String username, String email, String password){
        super();
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


//  Getters & Setters
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

    public List<Party> getOwnedParties() {
        return ownedParties;
    }

    public void setOwnedParties(List<Party> ownedParties) {
        this.ownedParties = ownedParties;
    }

    public List<Party> getJoinedParties() {
        return joinedParties;
    }

    public void setJoinedParties(List<Party> joinedParties) {
        this.joinedParties = joinedParties;
    }

//  Utility methods
    public boolean isMatchingPassword(String password) {
    return encoder.matches(password, pwHash);
}

    public void addOwnedParty(Party party) { this.ownedParties.add(party); }
    public void removeOwnedParty(Party party) { this.ownedParties.remove(party); }

    public void addJoinedParty(Party party) { this.joinedParties.add(party); }
    public void removeJoinedParty(Party party) { this.joinedParties.remove(party); }

    public Integer getUserID() {
        return getId();
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", wishLists=" + wishLists +
                ", owned parties=" + ownedParties +
                ", joined parties=" + joinedParties +
                '}';
    }

//    public void findWishLists(){
//        //find all wishlists created by THIS USER
//    }
//
//    public void findSupaSpecificWishList(){
//        //find a list by the WishList ID
//    }

    public Boolean isPartyOwner(User user1, User user2){
        if (user1.equals(user2)){
            return true;
        } else return false;
    }

    public void addToGroupCreatedByAnotherUser(Party party){
        joinedParties.add(party);
    }

    public void removeFromGroupCreatedByAnotherUser(Party party){
        joinedParties.remove(party);
    }

}
