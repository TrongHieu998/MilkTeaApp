package com.example.app_trasua.class_diemthuong;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_trasua.Model.Cart;
import com.example.app_trasua.Model.CartDetail;
import com.example.app_trasua.Model.Confirm;
import com.example.app_trasua.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_donhang extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<Confirm> cartList;
    private ArrayList<String> arrayListKey;

    // phúc làm
    private ArrayList<String> cartDetailKeys;
    private ArrayList<CartDetail> cartDetailArrayList;

    public Adapter_donhang(Context context, ArrayList<String> cartDetailKeys, ArrayList<CartDetail> cartDetailArrayList) {
        this.context = context;
        this.cartDetailKeys = cartDetailKeys;
        this.cartDetailArrayList = cartDetailArrayList;
    }

    public Adapter_donhang(Context context, List<Confirm> cartList, ArrayList<String> arrayListKey) {
        this.context = context;
        this.cartList = cartList;
        this.arrayListKey = arrayListKey;
    }

    public Adapter_donhang(Context context, List<Confirm> cartList) {
        this.context = context;
        this.cartList = cartList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(context).inflate(R.layout.dong_gio_hang, parent, false);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_ten.setText(cartDetailArrayList.get(position).getItemTen());
        holder.txt_gia.setText(cartDetailArrayList.get(position).getItemGia());
        holder.txt_soluong.setText(cartDetailArrayList.get(position).getItemSoluong());
    }

    @Override
    public int getItemCount() {
        return cartDetailArrayList.size();
    }
}
class ViewHolder extends RecyclerView.ViewHolder
{
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