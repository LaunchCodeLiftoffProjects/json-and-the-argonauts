package org.launchcode.giftlist.Models;

import java.util.List;
import java.util.Objects;

public class WishList {

    private int listID;

    private String name;
    private List items;
    private List groups;


    public WishList(String name, List items){
        this.items = items;
        this.name = name;
    }

    public WishList(){}


    public int getListID() {
        return listID;
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

    public List getGroups() {
        return groups;
    }

    public void setGroups(List groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return getListID() == wishList.getListID() && Objects.equals(getName(), wishList.getName()) && Objects.equals(getItems(), wishList.getItems()) && Objects.equals(getGroups(), wishList.getGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getListID(), getName(), getItems(), getGroups());
    }

    @Override
    public String toString() {
        return "WishList{" +
                "listID=" + listID +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", groups=" + groups +
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
