package restaurantObjects;

public class Beverage {

    class enum Size {
        Small, Medium, Large;
    }

    String name;
    Size size;
    double price;

    public Beverage(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //public setters
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}

    //public getters
    public String getName() {return this.name;}
    public String getPrice() {return this.price + "$";}

}