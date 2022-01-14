package org.launchcode.giftlist.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Item extends AbstractSuper {

    @NotBlank
    private String name;

    private String description;


    @ManyToOne
    @JoinColumn(name = "list_id")
    private WishList wishList;

    public WishList getWishlist() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }


    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Item(){
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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", wishLists=" + wishList +
                '}';
    }


    //waiting to add additional methods



}
