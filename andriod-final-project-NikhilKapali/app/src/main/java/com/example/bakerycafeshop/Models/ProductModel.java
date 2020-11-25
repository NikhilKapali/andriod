package com.example.bakerycafeshop.Models;

public class ProductModel {
    String dishImageName;
    String dishName;
    String Category;
    String Price;

    public String getDishImageName() {
        return dishImageName;
    }

    public void setDishImageName(String dishImageName) {
        this.dishImageName = dishImageName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public ProductModel(String dishImageName, String dishName, String category, String price) {
        this.dishImageName = dishImageName;
        this.dishName = dishName;
        Category = category;
        Price = price;
    }
}
