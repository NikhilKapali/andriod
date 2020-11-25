package com.example.bakerycafeshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakerycafeshop.Helpers.OnClickDish;
import com.example.bakerycafeshop.Models.ProductModel;
import com.example.bakerycafeshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuViewAdapter extends RecyclerView.Adapter<MenuViewAdapter.MenuViewHolder>{

    List<ProductModel> productModelList;
    Context context;
    Bitmap bitmap;
    public static final String BASE_URL= "http://10.0.2.2:3000";

    public MenuViewAdapter(List<ProductModel> productModelList, Context context) {
        this.productModelList = productModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View menuView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_item_list,viewGroup,false);
        return new MenuViewHolder(menuView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {
        final ProductModel productModel=productModelList.get(i);
        menuViewHolder.image_name.setText(productModel.getDishName());


        Picasso.with(context).load(BASE_URL+"/images/"+productModel.getDishImageName()).into(menuViewHolder.img);
        Log.d("log", "onBindViewHolder: "+BASE_URL+"/images/"+productModel.getDishImageName());


        Toast.makeText(context, ""+productModelList.size(), Toast.LENGTH_SHORT).show();



        menuViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context vcontext= v.getContext();
                String send_path=BASE_URL+"images/"+productModel.getDishImageName();

                System.out.println(send_path);

                Intent intent=new Intent(context, OnClickDish.class);

                intent.putExtra("dishName",productModel.getDishName());
                intent.putExtra("Price",productModel.getPrice());
                intent.putExtra("dishImageName",BASE_URL+"/images/"+productModel.getDishImageName());
                intent.putExtra("Category",productModel.getCategory());

                vcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        public TextView image_name;
        public CircleImageView img;
        RelativeLayout parent_layout;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            image_name=itemView.findViewById(R.id.image_name);
            img=itemView.findViewById(R.id.menu_image);
            parent_layout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
