package com.example.bakerycafeshop.Models;

public class ReservationModel {

    public String FirstName;
    public String LastName;

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String Email;
    public String Time;
    public String Message;
    public String DateTable;
    String tableName;
    String Category;
    String Price;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDateTable() {
        return DateTable;
    }

    public void setDateTable(String dateTable) {
        DateTable = dateTable;
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

    public ReservationModel(String firstName,String lastName, String email, String time, String message, String dateTable, String tableName, String category, String price) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Time = time;
        Message = message;
        DateTable = dateTable;
        this.tableName = tableName;
        Category = category;
        Price = price;
    }
}
