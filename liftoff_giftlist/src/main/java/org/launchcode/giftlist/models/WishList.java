package org.launchcode.giftlist.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
public class WishList extends AbstractSuper{

    @NotBlank
    private String name;

    private String description;

    @OneToMany(mappedBy = "wishList", cascade={CascadeType.ALL})
    private List<Item> items = new ArrayList<>();

    @ManyToMany(mappedBy = "memberWishLists")
    private List<Party> parties = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "list_owner_id")
    private User listOwner;


//  Constructors
    public WishList(String name, String description, User listOwner){
        this.name = name;
        this.description = description;
        this.listOwner = listOwner;
    }

    public WishList(){ }


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
    public User getListOwner() {
        return listOwner;
    }
    public void setListOwner(User listOwner) {
        this.listOwner = listOwner;
    }
    public List<Item> getItems() {
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

//  Utility methods
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

    @Override
    public String toString() {
        return "WishList{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", items=" + items +
            ", parties=" + parties +
            ", listOwner=" + listOwner +
            '}';
    }
}
