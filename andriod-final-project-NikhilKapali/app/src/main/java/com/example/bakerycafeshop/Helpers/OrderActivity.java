package com.example.bakerycafeshop.Helpers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.About_Us_Activity;
import com.example.bakerycafeshop.ContactUsActivity;
import com.example.bakerycafeshop.Models.OrderModel;
import com.example.bakerycafeshop.Models.ReviewModel;
import com.example.bakerycafeshop.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_view;
    EditText tv_name,tv_price,tv_category;
    EditText et_firstname, et_address, et_email, et_message, et_payment;
    Button orderFood;
    Bundle bundle;


    private static final String BASE_URL = "http://10.0.2.2:3000/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        img_view=findViewById(R.id.itmimage);
        tv_name=findViewById(R.id.itmname);
        tv_price=findViewById(R.id.itmprice);
        tv_category=findViewById(R.id.itmcategory);
        et_firstname = findViewById(R.id.et_Orderfname);
        et_address = findViewById(R.id.et_Orderaddress);
        et_email = findViewById(R.id.et_Orderemail);
        et_message = findViewById(R.id.et_Ordermessage);
        et_payment = findViewById(R.id.et_Orderpayment);


        orderFood = findViewById(R.id.btn_order_food);
        orderFood.setOnClickListener(this);

        tv_name.setText(getIntent().getStringExtra("dishName1"));
        tv_category.setText(getIntent().getStringExtra("Category1"));
        tv_price.setText(getIntent().getStringExtra("Price1"));
        //tv_name.setText(getIntent().getStringExtra("dishName1"));
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_order_food){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Master_Api masterApi = retrofit.create(Master_Api.class);

            String dishName = tv_name.getText().toString();
            String Category = tv_category.getText().toString();
            String Price = tv_price.getText().toString();

            String firstname = et_firstname.getText().toString();
            String address = et_address.getText().toString();
            String email = et_email.getText().toString();
            String message = et_message.getText().toString();
            String payment = et_payment.getText().toString();

            OrderModel orderModel = new OrderModel(dishName, Category, Price, firstname, address, email, message, payment);

            Call<Void> orderCall = masterApi.orderFood(orderModel);

            orderCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(OrderActivity.this, "Successful", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(OrderActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        }

    }
}
