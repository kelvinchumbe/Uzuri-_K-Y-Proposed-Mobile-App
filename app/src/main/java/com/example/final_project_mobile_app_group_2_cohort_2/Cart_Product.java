package com.example.final_project_mobile_app_group_2_cohort_2;

public class Cart_Product {
    String name, category, imageUrl;
    int price, quantity;

    public Cart_Product(){ }

    public Cart_Product(String name, int price, String category, int quantity, String imageUrl){
        this.name = name;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
