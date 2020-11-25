package com.example.bakerycafeshop.Models;

public class TableModel {
    String tableImageName;
    String tableName;
    String Category;
    String Price;

    public String getTableImageName() {
        return tableImageName;
    }

    public void setTableImageName(String tableImageName) {
        this.tableImageName = tableImageName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public TableModel(String tableImageName, String tableName, String category, String price) {
        this.tableImageName = tableImageName;
        this.tableName = tableName;
        Category = category;
        Price = price;
    }
}
