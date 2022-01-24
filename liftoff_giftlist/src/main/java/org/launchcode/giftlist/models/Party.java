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


    public Party(String name, String description, User partyOwner){
        this.name = name;
        this.description = description;
        this.partyOwner = partyOwner;
    }

    public Party(){
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

    public void addMember(User user){
        this.members.add(user);
    }

    public void removeMember(User user) {this.members.remove(user);}


}
