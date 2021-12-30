package org.launchcode.giftlist.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class WishList {

    @Id
    @GeneratedValue
    private int listID;

    private String name;

    @ManyToMany(mappedBy = "wishLists")
    private List<Item> items = new ArrayList<>();

    @ManyToMany(mappedBy = "memberWishLists")
    private List<Party> parties = new ArrayList<>();

    @ManyToOne
    private User listOwner;







    public WishList(String name, List items){
        this.items = items;
        this.name = name;
    }

    public WishList(){}


    public int getListID() {
        return listID;
    }

    public User getListOwner() {
        return listOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return getListID() == wishList.getListID() && Objects.equals(getName(), wishList.getName()) && Objects.equals(getItems(), wishList.getItems()) && Objects.equals(getParties(), wishList.getParties());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getListID(), getName(), getItems(), getParties());
    }

    @Override
    public String toString() {
        return "WishList{" +
                "listID=" + listID +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", parties=" + parties +
                '}';
    }

    /*public void connectWishList(){

    }*/
    public void addNewItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public int locateIndexOfItem(Item item){
        return items.indexOf(item);
    }

    public Item findItem(Item item){
        int foundItem = locateIndexOfItem(item);
        return (Item) items.get(foundItem);
    }



}
