package org.launchcode.giftlist.Models;

import java.util.List;

public class WishList {

    private int GroupID;

    private String name;
    private Item item;
    private List wishList;

    public WishList(Item item, String name, List wishList){
        this.item = item;
        this.name = name;
        this.wishList = wishList;
    }

    public WishList(){}


    public int getGroupID() {
        return GroupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List getWishList() {
        return wishList;
    }

    public void addItem(Item item){
        wishList.add(item);
    }

    public void removeItem(Item item){
        wishList.remove(item);
    }

    public int locateIndexOfItem(Item item){
        return wishList.indexOf(item);
    }

    public Item findItem(Item item){
        int foundItem = locateIndexOfItem(item);
        return (Item) wishList.get(foundItem);
    }

}
