package Models;

import java.util.Objects;

public class Item {

    private int itemID;

    private String name;
    private String User;
    private Double price;

    public Item(String name, Double price){
        this.name = name;
        this.price = price;
    }

    public Item(){
    }


    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getItemID() == item.getItemID() && getName().equals(item.getName()) && getUser().equals(item.getUser()) && getPrice().equals(item.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemID(), getName(), getUser(), getPrice());
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                ", User='" + User + '\'' +
                ", price=" + price +
                '}';
    }


    //waiting to add additional methods







}
