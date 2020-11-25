package com.example.bakerycafeshop;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bakerycafeshop.Helpers.OrderActivity;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    ImageButton menu, book_table, update, contact_us;
    ActionBar actionBar;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        menu = findViewById(R.id.menu_icon);
        book_table = findViewById(R.id.table_icon);
        update = findViewById(R.id.update_icon);
        contact_us = findViewById(R.id.contact_icon);

        menu.setOnClickListener(this);
        book_table.setOnClickListener(this);
        update.setOnClickListener(this);
        contact_us.setOnClickListener(this);

        proximity();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.menu_icon){
            Intent menu = new Intent(this, MenuActivity.class);
            startActivity(menu);
        }
        if (view.getId() == R.id.table_icon){
            Intent table = new Intent(this, BookTableActivity.class);
            startActivity(table);
        }
        if (view.getId() == R.id.update_icon){
            Intent update = new Intent(this, UpdateActivity.class);
            startActivity(update);
        }
        if (view.getId() == R.id.contact_icon){
            Intent contact = new Intent(this, ContactUsActivity.class);
            startActivity(contact);
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


    public void proximity()
    {
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensor == null)
        {
            Toast.makeText(this, "No sensor detected", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sensor Kicking in .....", Toast.LENGTH_SHORT).show();
        }

        SensorEventListener proximityListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                WindowManager.LayoutParams params = Dashboard.this.getWindow().getAttributes();
                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0) {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = 0;
                        getWindow().setAttributes(params);
                    } else {
                        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = -1f;
                        getWindow().setAttributes(params);
                    }
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proximityListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }
}
