package com.example.bakerycafeshop.Fragments;


import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bakerycafeshop.API.Master_Api;
import com.example.bakerycafeshop.Models.UserModel;
import com.example.bakerycafeshop.NotificationChannel.CreateChannel;
import com.example.bakerycafeshop.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    EditText firstName,lastName,Address,Phone,Email, Usertype, Password, profilePicture;

    private NotificationManagerCompat notificationManagerCompat;

    Button register;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String getGender;

    private static final String BASE_URL = "http://10.0.2.2:3000/";

    Master_Api masterApi;
    Retrofit retrofit;
    UserModel userModel;



    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        CreateChannel channel = new CreateChannel(getActivity());
        channel.createChannel();

        firstName= view.findViewById(R.id.et_fname);
        lastName= view.findViewById(R.id.et_lname);
        Address= view.findViewById(R.id.et_address);
        Phone= view.findViewById(R.id.et_number);
        Email= view.findViewById(R.id.et_email);
        Usertype= view.findViewById(R.id.et_usertype_res);
        Password= view.findViewById(R.id.et_password_res);

        register=view.findViewById(R.id.btn_register);
        register.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register){
            if(validate()){
                RegisterUser();
            }


        }

    }

    private void createInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        masterApi = retrofit.create(Master_Api.class);
    }

    private void RegisterUser(){
        createInstance();
        UserModel userModel=new UserModel( firstName.getText().toString(),
                lastName.getText().toString(),
                Address.getText().toString(),
                Phone.getText().toString(),
                Email.getText().toString(),
                Usertype.getText().toString(),
                Password.getText().toString());

        Call<Void> addcall = masterApi.registerUser(userModel);
        addcall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getActivity(), "Added", Toast.LENGTH_LONG).show();
                DisplayNotification();
                //UploadImage();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void UploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        createInstance();
        byte[] bytes = stream.toByteArray();
        try {
            File file = new File(getActivity().getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageName", file.getName(), rb);

            Call<String> imagecall = masterApi.uploadImage(body);
            imagecall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getActivity(), response.body() + "Hello", Toast.LENGTH_SHORT).show();
                    profilePicture.setText(response.body());

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "ERROR" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean validate(){
        if(TextUtils.isEmpty(firstName.getText().toString())){
            firstName.setError("Enter First name");
            firstName.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }
        if(TextUtils.isEmpty(lastName.getText().toString())){
            lastName.setError("Enter Last name");
            lastName.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }
        if(TextUtils.isEmpty(Address.getText().toString())){
            Address.setError("Enter Email");
            Address.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }
        if(TextUtils.isEmpty(Phone.getText().toString())){
            Phone.setError("Enter Phone");
            Phone.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }
        if(TextUtils.isEmpty(Usertype.getText().toString())){
            Usertype.setError(" ");
            Usertype.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }
        if (TextUtils.isEmpty(Password.getText().toString())){
            Password.setError("Enter password");
            Password.requestFocus();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            return false;
        }

        return true;
    }
    private void  DisplayNotification(){
        Notification notification = new NotificationCompat.Builder(getActivity(), CreateChannel.Notification_2)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("Registered Successfully")
                .setContentText("You Have been Registered into the System Successfully")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1, notification);
    }
}
