package Models;

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
    //waiting to add additional methods







}
