package com.example.bakerycafeshop.Helpers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakerycafeshop.About_Us_Activity;
import com.example.bakerycafeshop.Models.ProductModel;
import com.example.bakerycafeshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OnClickDish extends AppCompatActivity implements View.OnClickListener{
    ImageView img_view;
    TextView tv_name,tv_price,tv_category;
    Bundle bundle;
    public static final String BASE_URL= "http://10.0.2.2:3000";
    List<ProductModel> productModelList;
    Button orderForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_dish);
        bundle=getIntent().getExtras();

        img_view=findViewById(R.id.itmimage);
        tv_name=findViewById(R.id.itmname);
        tv_price=findViewById(R.id.itmprice);
        tv_category=findViewById(R.id.itmcategory);

        orderForm = findViewById(R.id.btn_order_form);
        orderForm.setOnClickListener(this);

        if(bundle !=null)
        {
            tv_name.setText(bundle.getString("dishName"));
            tv_price.setText(bundle.getString("Price"));
            tv_category.setText(bundle.getString("Category"));
            String image=bundle.getString("dishImageName");

            Picasso.with(this).load(image).into(img_view);
        }
        Toast.makeText(this,"Dish Name" + tv_name.getText().toString() , Toast.LENGTH_LONG).show();

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
        if (view.getId() == R.id.btn_order_form){

            Context vcontext= view.getContext();

            Intent intent=new Intent(this, OrderActivity.class);

//            intent.putExtra("dishImageName",img_view.get().toString());
            intent.putExtra("dishName1",tv_name.getText().toString());
            intent.putExtra("Price1",tv_price.getText().toString());
            intent.putExtra("Category1",tv_category.getText().toString());

//            intent.putExtra("",productModel.getPrice());
//            intent.putExtra("dishImageName",BASE_URL+"/images/"+productModel.getDishImageName());
//            intent.putExtra("",productModel.getCategory());

            vcontext.startActivity(intent);
        }
    }
}
