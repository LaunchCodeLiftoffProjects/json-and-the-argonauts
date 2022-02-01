package org.launchcode.giftlist.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Party extends AbstractSuper{

    @NotBlank
    private String name;

    private String description;

    @ManyToOne
    private User partyOwner;

    @ManyToMany
    private List<User> members = new ArrayList<>();

    @ManyToMany
    private List<WishList> memberWishLists = new ArrayList<>();


//  Constructors
    public Party(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Party(){}


//  Getters & Setters
    public String getName() {
    return name;
}

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getPartyOwner() {
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

    public List<WishList> getMemberWishLists() { return memberWishLists; }

    public void setMemberWishLists(List<WishList> memberWishLists) { this.memberWishLists = memberWishLists; }


//  Utility methods
    public void addMember(User user){
        this.members.add(user);
    }

    public void removeMember(User user) { this.members.remove(user);}

    public void addWishList(WishList wishList) { this.memberWishLists.add(wishList);}

    public void removeWishList(WishList wishList) { this.memberWishLists.remove(wishList);}

    public boolean isOwnedBy(User user) {
        if (this.getPartyOwner().equals(user)) {
            return true;
        } else {
            return false;
        }
    }

//    if

    @Override
    public String toString() {
        return "Party{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", partyOwner=" + partyOwner +
            ", members=" + members +
            ", memberWishLists=" + memberWishLists +
            '}';
    }


}
