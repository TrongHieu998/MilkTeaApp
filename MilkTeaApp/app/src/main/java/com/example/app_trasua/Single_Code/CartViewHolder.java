package com.example.app_trasua.Single_Code;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_trasua.Interface.ItemClickListner;
import com.example.app_trasua.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_ten,txt_gia,txt_soluong,txt_tongtien;
    public ImageView imageView;
    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.img_dongGioHang);
        txt_ten=itemView.findViewById(R.id.txt_ten_dongGioHang);
        txt_gia=itemView.findViewById(R.id.txt_gia_dongGioHang);
        txt_soluong=itemView.findViewById(R.id.txt_soluong_dongGioHang);
        txt_tongtien=itemView.findViewById(R.id.txt_tongtien_cart);
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
