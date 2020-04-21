package com.example.app_trasua.Single_Code;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_trasua.Interface.ItemClickListner;
import com.example.app_trasua.Model.Products;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.FoodData;

import java.util.ArrayList;
import java.util.List;


public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_ten,txt_gia,txt_chitiet;
    public ImageView imageView;
    public ItemClickListner listner;
    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.img_milktea);
        txt_ten=itemView.findViewById(R.id.txt_MT_Ten);
        txt_gia=itemView.findViewById(R.id.txt_MT_Tien);
        txt_chitiet=itemView.findViewById(R.id.txt_MT_chitiet);
    }
    public void setItemClickListner (ItemClickListner listner)
    {
        this.listner=listner;
    }
    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(),false);
    }


}
