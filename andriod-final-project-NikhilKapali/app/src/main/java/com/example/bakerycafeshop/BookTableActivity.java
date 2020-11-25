package com.example.bakerycafeshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.Adapter.MenuViewAdapter;
import com.example.bakerycafeshop.Adapter.TableViewAdapter;
import com.example.bakerycafeshop.Models.ProductModel;
import com.example.bakerycafeshop.Models.TableModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookTableActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Retrofit retrofit;

    private static final String BASE_URL = "http://10.0.2.2:3000/";

    Master_Api masterAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        recyclerView=findViewById(R.id.table_Recycle_View);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);

        getTable();
    }
    public void getTable()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        masterAPI=retrofit.create(Master_Api.class);

        Call<List<TableModel>> tableListCall=masterAPI.getTable();
        tableListCall.enqueue(new Callback<List<TableModel>>() {
            @Override
            public void onResponse(Call<List<TableModel>> call, Response<List<TableModel>> response) {
                List<TableModel> tableModelList=response.body();
                recyclerView.setAdapter(new TableViewAdapter(tableModelList,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<TableModel>> call, Throwable t) {

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

}
