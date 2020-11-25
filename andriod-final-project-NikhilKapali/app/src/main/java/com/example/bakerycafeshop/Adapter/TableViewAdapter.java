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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakerycafeshop.Helpers.OnClickDish;
import com.example.bakerycafeshop.Helpers.OnClickTable;
import com.example.bakerycafeshop.Models.ProductModel;
import com.example.bakerycafeshop.Models.TableModel;
import com.example.bakerycafeshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TableViewAdapter extends RecyclerView.Adapter<TableViewAdapter.TableViewHolder> {

    List<TableModel> tableModelList;
    Context context;
    Bitmap bitmap;
    public static final String BASE_URL= "http://10.0.2.2:3000";

    public TableViewAdapter(List<TableModel> tableModelList, Context context) {
        this.tableModelList = tableModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View tableView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.table_item_list,viewGroup,false);
        return new TableViewAdapter.TableViewHolder(tableView);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder tableViewHolder, int i) {
        final TableModel tableModel= tableModelList.get(i);
        tableViewHolder.image_name.setText(tableModel.getTableName());


        Picasso.with(context).load(BASE_URL+"/imageTable/"+tableModel.getTableImageName()).into(tableViewHolder.img);
        Log.d("log", "onBindViewHolder: "+BASE_URL+"/imageTable/"+tableModel.getTableImageName());


        Toast.makeText(context, ""+tableModelList.size(), Toast.LENGTH_SHORT).show();



        tableViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context vcontext= v.getContext();
                String send_path=BASE_URL+"imageTable/"+tableModel.getTableImageName();

                System.out.println(send_path);

                Intent intent=new Intent(context, OnClickTable.class);
                intent.putExtra("tableName",tableModel.getTableName());
                intent.putExtra("Price",tableModel.getPrice());
                intent.putExtra("tableImageName",BASE_URL+"/imageTable/"+tableModel.getTableImageName());
                intent.putExtra("Category",tableModel.getCategory());

                vcontext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tableModelList.size();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder{

        public TextView image_name;
        public CircleImageView img;
        RelativeLayout parent_layout_table;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            image_name=itemView.findViewById(R.id.image_name);
            img=itemView.findViewById(R.id.table_image);
            parent_layout_table = itemView.findViewById(R.id.parent_layout_table);

        }
    }
}
