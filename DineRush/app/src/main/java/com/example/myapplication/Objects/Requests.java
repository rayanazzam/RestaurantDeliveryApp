package com.example.myapplication.Objects;

import java.util.List;

public class Requests {
    private String username, fullName, address, total;
    private List<Order> foods;

    public Requests (){}

    public Requests (String username, String fullName, String address, String total, List<Order> foods) {
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.total = total;
        this.foods = foods;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
