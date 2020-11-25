package com.example.bakerycafeshop.BusinessLogic;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.Models.Authtoken;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class LoginBusinessLogic {
    private String email;
    private String password;
    Authtoken authtoken;
    private static final String BASE_URL = "http://10.0.2.2:3000/";

    Retrofit retrofit;

    public LoginBusinessLogic(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Authtoken  checkUser(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Master_Api master_api = retrofit.create(Master_Api.class);

        Call<Authtoken> Logincall = master_api.login(email, password);
        try {
            Response<Authtoken> loginResponse = Logincall.execute();
            authtoken = loginResponse.body();

        } catch (IOException e) {
            e.printStackTrace();

        }

        return authtoken;
    }
}
