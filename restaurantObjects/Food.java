package restaurantObjects;

public class Food {

    enum FoodType {
        BreakFast, Lunch, Dinner, AllDay;
    }

    private FoodType foodType;
    private String name;
    private double price;
    // By default, upon instantiation, food is assume to be in stock
    //unless provided otherwise
    private boolean inStock; 

    // since foodType is an enum, make sure when instantiating a food object, 
    // you do FoodType.valueOf("some_foodType as string")
    // cannot change foodType once updated
    public Food(String name, FoodType foodType, Double Price) {
        this.foodType = foodType;
        this.name = name;
        this.priece = price;
        this.inStock = true;
    }
    
    //public setters
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}
    public void outOfStock () {this.inStock = false;}
    public void putInStock () {this.stock = true;}

    //public getters
    public String getName() {return this.name;}
    public String getPrice() {return this.price + "$";}
    public boolean isInStock () {return this.inStock;} 

}
