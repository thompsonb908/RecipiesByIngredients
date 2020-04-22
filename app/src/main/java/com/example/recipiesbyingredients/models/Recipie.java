package com.example.recipiesbyingredients.models;

import java.io.Serializable;
import java.util.List;

public class Recipie implements Serializable {

    private String name;
    private List<Ingredient> ingredients;
    private List<String> instructions;
    private String imgURL;

    public Recipie(String name, List<Ingredient> ingredients, List<String> instructions, String imgURL) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
