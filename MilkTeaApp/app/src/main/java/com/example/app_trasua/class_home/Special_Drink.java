package com.example.app_trasua.class_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.app_trasua.Admin.UpLoad;
import com.example.app_trasua.Model.Products;
import com.example.app_trasua.R;
import com.example.app_trasua.Single_Code.AddDetail_Special;
import com.example.app_trasua.Single_Code.ProductsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Special_Drink extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Products> mFoodList;
    FoodData mFoodData;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    ImageView imageView,imageV;
    EditText edt_timkiem;
    ProductsViewHolder myAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk_tea);
//        progressDialog= new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
        mRecyclerView=findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        edt_timkiem=(EditText) findViewById(R.id.edt_timkiem);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(Special_Drink.this,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference("SanPham").child("Special Drink");
//        progressDialog.show();
//        progressDialog.dismiss();
//        edt_timkiem.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
//        imageView=findViewById(R.id.img_close_detail);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iten=new Intent(Special_Drink.this, MainActivity.class);
//                startActivity(iten);
//                finish();
//            }
//        });
//        imageV=(ImageView) findViewById(R.id.img_giohang_classs) ;
//        imageV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iten=new Intent(Special_Drink.this, GioHang.class);
//                startActivity(iten);
//                finish();
//            }
//        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions <Products> options=
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(databaseReference,Products.class)
                        .build();
        FirebaseRecyclerAdapter<Products, ProductsViewHolder> adapter=
                new FirebaseRecyclerAdapter<Products, ProductsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull final Products model) {
                        holder.txt_ten.setText(model.getItemTen());
                        holder.txt_gia.setText(model.getItemGia());
                        Picasso.get().load(model.getItemImg()).into(holder.imageView);
                        holder.txt_chitiet.setText(model.getItemChitiet());

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent= new Intent(Special_Drink.this, AddDetail_Special.class);
                                intent.putExtra("itemId",model.getItemId());
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_milk_tea,parent,false);
                        ProductsViewHolder holder= new ProductsViewHolder(view);
                        return holder;
                    }
                };
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
//    private void filter(String text) {
//        ArrayList<Products> filterList= new ArrayList<>();
//        for(Products item:mFoodList)
//        {
//            if(item.getItemTen().toLowerCase().contains(text.toLowerCase()))
//            {
//                filterList.add(item);
//            }
//        }
//        myAdapter.filteredList(filterList);
//    }
    public void btn_up(View view) {
        startActivity(new Intent(this, UpLoad.class));
    }

}
