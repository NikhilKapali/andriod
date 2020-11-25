package com.example.bakerycafeshop.Helpers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakerycafeshop.About_Us_Activity;
import com.example.bakerycafeshop.R;
import com.squareup.picasso.Picasso;

public class OnClickTable extends AppCompatActivity {
    ImageView img_view;
    TextView tv_name,tv_price,tv_category;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_table);
        bundle=getIntent().getExtras();

        img_view=findViewById(R.id.tblimage);
        tv_name=findViewById(R.id.tblname);
        tv_price=findViewById(R.id.tblprice);
        tv_category=findViewById(R.id.tblcategory);

        if(bundle !=null)
        {
            tv_name.setText(bundle.getString("tableName"));
            tv_price.setText(bundle.getString("Price"));
            tv_category.setText(bundle.getString("Category"));
            String image=bundle.getString("tableImageName");

            Picasso.with(this).load(image).into(img_view);
        }
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
