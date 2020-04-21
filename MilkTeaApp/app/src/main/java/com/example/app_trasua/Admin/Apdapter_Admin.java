package com.example.app_trasua.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.FoodData;

import java.util.ArrayList;
import java.util.List;

public class Apdapter_Admin extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FoodData> mFoodList;
    private double tt = 0;
    private int sl = 0;

    public Apdapter_Admin(Context context, List<FoodData> mFoodList) {
        this.context = context;
        this.mFoodList = mFoodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(context).inflate(R.layout.dong_admin, parent, false);
        return new ViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Glide.with(context)
                .load(mFoodList.get(position).getItemImg())
                .into(holder.imageView);
        holder.txt_ten.setText(mFoodList.get(position).getItemTen());
        holder.txt_gia.setText(mFoodList.get(position).getItemGia());
        // Picasso.get().load(mFoodList.get(position).getItemImg()).into(holder.imageView);
        holder.mCardView_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTiet_Admin.class);
                intent.putExtra("Image", mFoodList.get(holder.getAdapterPosition()).getItemImg());
                intent.putExtra("Ten", mFoodList.get(holder.getAdapterPosition()).getItemTen());
                intent.putExtra("ChiTiet", mFoodList.get(holder.getAdapterPosition()).getItemChitiet());
                intent.putExtra("Gia", mFoodList.get(holder.getAdapterPosition()).getItemGia());
                //Chõ này là xóa
                intent.putExtra("keyValue", mFoodList.get(holder.getAdapterPosition()).getKey());
                //Chõ này là xóa
                double tam = ((Double.valueOf(mFoodList.get(holder.getAdapterPosition()).getItemGia())));
                tt = tt + tam;
                String g = String.valueOf(tt);
                intent.putExtra("TongTien", g);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterList) {
        mFoodList = filterList;
        notifyDataSetChanged();
    }
}
class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_ten, txt_gia;
        CardView mCardView_Admin;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_ten = (TextView) itemView.findViewById(R.id.txt_MT_Ten_admin);
        txt_gia = (TextView) itemView.findViewById(R.id.txt_MT_Tien_admin);
        imageView = (ImageView) itemView.findViewById(R.id.img_milktea_admin);
        mCardView_Admin = (CardView) itemView.findViewById(R.id.myCardView_admin);
    }
}