package com.example.bakerycafeshop.Fragments;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.BusinessLogic.LoginBusinessLogic;
import com.example.bakerycafeshop.Dashboard;
import com.example.bakerycafeshop.MenuActivity;
import com.example.bakerycafeshop.Models.Authtoken;
import com.example.bakerycafeshop.Models.UserModel;
import com.example.bakerycafeshop.NotificationChannel.CreateChannel;
import com.example.bakerycafeshop.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

import static java.lang.Boolean.TRUE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private NotificationManagerCompat notificationManagerCompat;
    Button login;
    EditText ed_email, ed_pass;
    Boolean isLoggedIn=false;

    private static final String BASE_URL = "http://10.0.2.2:3000/";

    Master_Api masterApi;
    Retrofit retrofit;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        CreateChannel channel = new CreateChannel(getActivity());
        channel.createChannel();

        ed_email = view.findViewById(R.id.et_email);
        ed_pass = view.findViewById(R.id.et_password);

        login = view.findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        preferences = getActivity().getSharedPreferences("APP", Context.MODE_PRIVATE);
        editor = preferences.edit();
        return view;
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {

            if (validate()) {
                createInstance();

                final String email = ed_email.getText().toString().trim();
                final String password = ed_pass.getText().toString().trim();

                LoginBusinessLogic loginBusinessLogic = new LoginBusinessLogic(email, password);
                StrictMode();
                Authtoken userData = loginBusinessLogic.checkUser();

                preferences = getActivity().getSharedPreferences("UserData", 0);

                editor = preferences.edit();

                editor.putString("token", userData.getToken());
                editor.putString("uid", userData.getUsers().get_id());

                Toast.makeText(getActivity(), "User id:" + userData.getUsers().get_id(), Toast.LENGTH_SHORT).show();

                editor.commit();

                DisplayNotification();

                Intent intent = new Intent(getActivity(), Dashboard.class);
                startActivity(intent);
                getActivity().finish();
            }
            else {
                Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(2000);
            }
        }
    }
    private void  DisplayNotification(){
        Intent intent = new Intent(getActivity(), LoginFragment.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.drawable.ic_notifications_active_black_24dp, getString(R.string.wearTitle)
                , pendingIntent).build();

        Notification notification = new NotificationCompat.Builder(getActivity(), CreateChannel.Notification_1)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("Logged in Successfully")
                .setContentText("The System Has Detected a Login Through your Account")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .build();
        notificationManagerCompat.notify(1, notification);
    }
    private boolean validate(){
        if(TextUtils.isEmpty(ed_email.getText().toString())){
            ed_email.setError("Enter Username");
            ed_email.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }
        if (TextUtils.isEmpty(ed_pass.getText().toString())){
            ed_pass.setError("Enter password");
            ed_pass.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }

        return true;
    }
private void StrictMode(){
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
}

}
