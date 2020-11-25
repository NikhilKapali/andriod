package com.example.bakerycafeshop.Models;

public class OrderModel {
    public String FirstName;
    public String Email;
    public String Address;
    public String Message;
    public String Payment;
    public String dishName;
    public String Category;
    public String Price;

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
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

    public OrderModel(String firstName, String email, String address, String message, String payment, String dishName, String category, String price) {
        FirstName = firstName;
        Email = email;
        Address = address;
        Message = message;
        Payment = payment;
        this.dishName = dishName;
        Category = category;
        Price = price;
    }
}
