package org.launchcode.giftlist.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Item extends AbstractSuper {

    @NotBlank
    private String name;

    private String description;

    private Boolean isPurchased = false;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private WishList wishList;


//  Constructors
    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Item(){
    }

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

    public void setDescription(String description) { this.description = description; }

    public WishList getWishlist() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

//  Utility methods

    @Override
    public String toString() {
        return name + ":  " + description + "\n";
    }

    public Boolean getPurchased() {
        return isPurchased;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
    }


    //waiting to add additional methods

}
