package org.launchcode.giftlist.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Party {

    @Id
    @GeneratedValue
    private int partyID;

    @ManyToOne
    private User partyOwner;

    @ManyToMany(mappedBy = "joinedParties")
    private List<User> members = new ArrayList<>();

    @ManyToMany
    private List<WishList> memberWishLists = new ArrayList<>();



    public int getPartyID() {
        return partyID;
    }

    public User getOwner() {
        return partyOwner;
    }

    public void setPartyOwner(User owner) {
        this.partyOwner = owner;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

}
