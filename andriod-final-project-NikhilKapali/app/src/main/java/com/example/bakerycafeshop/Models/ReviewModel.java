package com.example.bakerycafeshop.Models;

public class ReviewModel {
    public String FirstName;
    public String LastName;
    public String Email;
    public String Message;
    public String Rating;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public ReviewModel(String firstName, String lastName, String email, String message, String rating) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Message = message;
        Rating = rating;
    }
}
