package com.example.bakerycafeshop.NotificationChannel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class CreateChannel {
    Context context;
    public final static String Notification_1 = "Notification1";
    public final static String Notification_2 = "Notification2";

    public CreateChannel(Context context) {
        this.context = context;
    }

    public void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
              Notification_1,
              "Notification 1",
              NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Logged into the System Successfully");

            NotificationChannel channel2 = new NotificationChannel(
                    Notification_2,
                    "Notification 2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("Registered into the System Successfully");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
