package com.example.bakerycafeshop.Models;

public class Authtoken {

    private String token;
    private UserModel users;

    public Authtoken(String token, UserModel users) {
        this.token = token;
        this.users = users;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUsers() {
        return users;
    }

    public void setUsers(UserModel users) {
        this.users = users;
    }
}
