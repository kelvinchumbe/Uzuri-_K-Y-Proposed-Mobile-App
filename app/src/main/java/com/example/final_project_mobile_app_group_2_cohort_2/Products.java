package com.example.final_project_mobile_app_group_2_cohort_2;

import android.graphics.Bitmap;

public class Products {
    private String name;
    private String image;
    private int price;
    private String category;
    private float rating;
    private String description;

    public Products(){}

    public Products(String name, int price, String category, float rating, String description, String image){
        this.name = name;
        this.price = price;
        this.category = category;
        this.rating = rating;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
