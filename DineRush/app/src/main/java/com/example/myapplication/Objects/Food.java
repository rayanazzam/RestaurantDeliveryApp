package com.example.myapplication.Objects;

public class Food {
    private String name, imgSrc, description, menuId, price, inStock;


    public Food() {
    }

    public Food(String name, String imgSrc, String description, String menuId, String price, String inStock) {
        this.name = name;
        this.imgSrc = imgSrc;
        this.description = description;
        this.price = price;
        this.menuId = menuId;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId( String menuId) {
        this.menuId = menuId;
    }

    public String isInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
}
