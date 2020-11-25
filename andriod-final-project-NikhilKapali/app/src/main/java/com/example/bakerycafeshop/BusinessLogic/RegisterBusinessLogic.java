package com.example.bakerycafeshop.BusinessLogic;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.Models.Authtoken;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterBusinessLogic {
    private String email;
    private String password;
    Authtoken authtoken;
    private static final String BASE_URL = "http://10.0.2.2:3000/";

    Retrofit retrofit;

    public RegisterBusinessLogic(String email, String password) {
//        this.FirstName = firstName;
//        this.LastName = lastName;
//        this.Address = address;
//        this.PhNumber = phNumber;
//        this.Email = email;
//        this.Usertype = usertype;
//        this.Password = password;
//
//        this.email = email;
//        this.password = password;
    }

    public void Register(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Master_Api master_api = retrofit.create(Master_Api.class);
    }

}
