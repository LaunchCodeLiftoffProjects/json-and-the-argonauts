package org.launchcode.giftlist.models.dto;

import org.launchcode.giftlist.models.Item;
import org.launchcode.giftlist.models.WishList;

import java.util.List;

public class ViewUserWishListDTO {


    private List<WishList> wishLists;

    private List<Item> items;

    private Item item;

    private Boolean isPurchased;

    public List<WishList> getWishList() {
        return wishLists;
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem() {
        return item;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
    }
}
