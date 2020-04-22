package com.example.recipiesbyingredients.models;

public class Ingredient {

    private String name;
    private String quantity;
    private int  id = -1;

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String toString(){
        return quantity + ": " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}