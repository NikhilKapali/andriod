package com.example.bakerycafeshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.Models.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    Button selectimage, update;
    EditText firstname, lastname, address, number, email, usertype, password;
    Boolean isLoggedIn=false;

    private static final String BASE_URL = "http://10.0.2.2:3000/";

    Master_Api masterApi;
    Retrofit retrofit;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        LoadUserDetail();


        firstname = findViewById(R.id.et_fname);
        lastname = findViewById(R.id.et_lname);
        address = findViewById(R.id.et_address);
        number = findViewById(R.id.et_number);
        email = findViewById(R.id.et_email);
        usertype = findViewById(R.id.et_usertype_res);
        password = findViewById(R.id.et_password_res);


        update = findViewById(R.id.btn_update);
        update.setOnClickListener(this);

        selectimage = findViewById(R.id.btn_update_selectimage);
        selectimage.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update){

            updateProfile();
        }

        if (view.getId() == R.id.btn_update_selectimage){

        }

    }

    private void createInstance(){
//            Gson gson=new GsonBuilder()
//                    .setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        masterApi = retrofit.create(Master_Api.class);
    }
    private void LoadUserDetail() {
        createInstance();

        preferences = getSharedPreferences("UserData", 0);

        String userid = preferences.getString("uid", null);
        Toast.makeText(UpdateActivity.this, "User id:"+ userid, Toast.LENGTH_SHORT).show();

        Call<UserModel> userModelCall = masterApi.loadprofile(userid);

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                UserModel userModel = response.body();
                firstname.setText(userModel.getFirstname());
               // Toast.makeText(UpdateActivity.this, "Fistname" + userModel.getFirstName(), Toast.LENGTH_LONG).show();
                lastname.setText(userModel.getLastname());
                address.setText(userModel.getAddress());
                email.setText(userModel.getEmail());
                number.setText(userModel.getPhone());
                password.setText(userModel.getPassword());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void updateProfile()
    {
        createInstance();

        String newFirstName,newLastName,newEmail,newAddress, newUserType, newPassword,newPhoneNumber;

        newFirstName=firstname.getText().toString();
        newLastName=lastname.getText().toString();
        newEmail=email.getText().toString();
        newAddress=address.getText().toString();
        newUserType=usertype.getText().toString();
        newPhoneNumber=number.getText().toString();

        newPassword=password.getText().toString();

        SharedPreferences preferences=(UpdateActivity.this).getSharedPreferences("UserData",0);
        String userId=preferences.getString("uid",null);

        Toast.makeText(this, "User id: +" +userId, Toast.LENGTH_SHORT).show();

        Call<String> updateProfileData = masterApi.updateProfile(userId, newFirstName, newLastName, newEmail, newUserType, newPhoneNumber, newAddress, newPassword);

        updateProfileData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(UpdateActivity.this, "Profile Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateActivity.this, Dashboard.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_pannel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_1){
            Intent item_intent = new Intent(this, About_Us_Activity.class);
            Toast.makeText(this, "Welcome to the About Us Page", Toast.LENGTH_LONG).show();
            startActivity(item_intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

}
