package com.example.bakerycafeshop.API;

import com.example.bakerycafeshop.Models.Authtoken;
import com.example.bakerycafeshop.Models.OrderModel;
import com.example.bakerycafeshop.Models.ProductModel;
import com.example.bakerycafeshop.Models.ReservationModel;
import com.example.bakerycafeshop.Models.ReviewModel;
import com.example.bakerycafeshop.Models.TableModel;
import com.example.bakerycafeshop.Models.UserModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Master_Api {
    @FormUrlEncoded
    @POST("userLoginAndroid")
    Call<Authtoken> login(@Field("email") String email, @Field("password") String password);

    @POST("userUpload")
    Call<Void> registerUser(@Body UserModel userModel);

    @Multipart
    @POST("uploadProfilePicture")
    Call<String> uploadImage(@Part MultipartBody.Part body);

    @POST("ReviewUser")
    Call<Void> addreview(@Body ReviewModel reviewModel);

    @POST("FoodOrder")
    Call<Void> orderFood(@Body OrderModel orderModel);

    @POST("BookTable")
    Call<Void> bookTable(@Body ReservationModel reservationModel);

    @GET("getMenu")
    Call<List<ProductModel>> getDish();

    @GET("getTable")
    Call<List<TableModel>> getTable();


    @GET("getUserById/{id}")
    Call<UserModel> loadprofile(@Path("id") String id);

    @FormUrlEncoded
    @PUT("UserUpdateAndroid")
    Call<String> updateProfile(@Field("_id") String uid,@Field("firstname") String firstname, @Field("lastname") String lastname, @Field("email")String email, @Field("usertype") String usertype, @Field("phone") String phone, @Field("address") String address, @Field("password") String password);



}
