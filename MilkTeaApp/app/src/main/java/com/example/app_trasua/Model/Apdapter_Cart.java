package com.example.app_trasua.Model;

import android.content.Context;


import com.example.app_trasua.R;

import java.util.List;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Apdapter_Cart extends  RecyclerView.Adapter<ViewHolder>{

    private Context context;
    private List<CartDetail> cartDetails;
    private double tt=0;
    private int sl=0;

    public Apdapter_Cart(Context context, List<CartDetail> cartDetails) {
        this.context = context;
        this.cartDetails = cartDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(context).inflate(R.layout.dong_gio_hang, parent, false);
        return new ViewHolder(mview);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_ten.setText(cartDetails.get(position).getItemTen());
        holder.txt_gia.setText(cartDetails.get(position).getItemGia());
        holder.txt_soluong.setText(cartDetails.get(position).getItemSoluong());
    }

    @Override
    public int getItemCount() {
        return cartDetails.size();
    }
}
class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView txt_ten, txt_gia,txt_soluong;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_ten = (TextView) itemView.findViewById(R.id.txt_ten_dongGioHang);
        txt_gia = (TextView) itemView.findViewById(R.id.txt_gia_dongGioHang);
        txt_soluong=(TextView)itemView.findViewById(R.id.txt_soluong_dongGioHang);
        imageView = (ImageView) itemView.findViewById(R.id.img_dongGioHang);
    }
}
