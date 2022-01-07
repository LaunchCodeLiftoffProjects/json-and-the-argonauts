package org.launchcode.giftlist.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class WishList extends AbstractSuper{

    private String name;

    @ManyToMany(mappedBy = "wishLists")
    private List<Item> items = new ArrayList<>();

    @ManyToMany(mappedBy = "memberWishLists")
    private List<Party> parties = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "list_owner_id")
    private User listOwner;


    public WishList(String name, List items){
        this.items = items;
        this.name = name;
    }

    public WishList(){}

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
