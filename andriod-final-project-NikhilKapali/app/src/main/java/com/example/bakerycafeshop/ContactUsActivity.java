package com.example.bakerycafeshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.Models.ReviewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtFirstname, txtLastname, txtmail, txtmsg, rating;
    private Button btnReview;

    private static final String BASE_URL = "http://10.0.2.2:3000/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        txtFirstname = findViewById(R.id.your_name);
        txtLastname = findViewById(R.id.last_name);
        txtmail = findViewById(R.id.your_email);
        txtmsg = findViewById(R.id.your_message);
        rating = findViewById(R.id.your_rating);
        btnReview = findViewById(R.id.btn_review);

        btnReview.setOnClickListener(this);
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
        if (view.getId() == R.id.btn_review){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Master_Api masterApi = retrofit.create(Master_Api.class);

        String firstname = txtFirstname.getText().toString();
        String lastname = txtLastname.getText().toString();
        String email = txtmail.getText().toString();
        String message = txtmsg.getText().toString();
        String rate = rating.getText().toString();

        ReviewModel reviewModel = new ReviewModel(firstname, lastname, email, message, rate);

        Call<Void> reviewCall = masterApi.addreview(reviewModel);

        reviewCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ContactUsActivity.this, "Successful", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(ContactUsActivity.this, "Error", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    }
}
