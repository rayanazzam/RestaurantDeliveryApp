package com.example.myapplication.Objects;

public class Category {

    private String name;
    private String imgSrc;

    public Category () {
    }
    public Category(String name, String imgSrc) {
        this.name = name;
        this.imgSrc = imgSrc;
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
}
