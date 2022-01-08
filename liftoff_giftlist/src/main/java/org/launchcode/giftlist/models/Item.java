package org.launchcode.giftlist.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Item extends AbstractSuper {


    private String name;
    private Double price;
    private String user;

    @ManyToMany
    private List<WishList> wishLists = new ArrayList<>();

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public Item(String name, Double price){
        this.name = name;
        this.price = price;
    }

    public Item(){
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        user = user;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", price=" + price +
                ", wishLists=" + wishLists +
                '}';
    }


    //waiting to add additional methods



}
