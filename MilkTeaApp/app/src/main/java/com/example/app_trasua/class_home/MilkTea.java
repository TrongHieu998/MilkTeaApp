package com.example.app_trasua.class_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.app_trasua.MainActivity;
import com.example.app_trasua.Model.Products;
import com.example.app_trasua.R;
import com.example.app_trasua.Single_Code.CartActivity;
import com.example.app_trasua.Single_Code.ProductDetailActivity;
import com.example.app_trasua.Single_Code.ProductsViewHolder;
import com.example.app_trasua.class_diemthuong.GioHang;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MilkTea extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<FoodData> mFoodList;
    FoodData mFoodData;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    ImageView imgView,imageV;
    EditText edt_timkiem;
    Adapter_Milk_Tea myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk_tea);
        mRecyclerView=findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        edt_timkiem=(EditText) findViewById(R.id.edt_timkiem);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(MilkTea.this,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
       // progressDialog= new ProgressDialog(this);
     //   progressDialog.setMessage("Loading...");
        databaseReference= FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea");
        imageV=findViewById(R.id.img_giohang_classs);
        imageV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MilkTea.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //progressDialog.show();
//        mRecyclerView = findViewById(R.id.recycleView);
//        edt_timkiem = (EditText) findViewById(R.id.edt_timkiem);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(MilkTea.this, 2);
//        mRecyclerView.setLayoutManager(gridLayoutManager);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//
//        mFoodList = new ArrayList<>();
//        myAdapter = new Adapter_Milk_Tea(MilkTea.this, mFoodList);
//        mRecyclerView.setAdapter(myAdapter);
//        databaseReference = FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea");
//        progressDialog.show();
//        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mFoodList.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    FoodData foodData = snapshot.getValue(FoodData.class);
//                    // Chỗ này là xóa
//                    foodData.setKey(snapshot.getKey());
//                    //Chõ này là xóa
//                    mFoodList.add(foodData);
//                }
//                myAdapter.notifyDataSetChanged();
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                progressDialog.dismiss();
//            }
//        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options=
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
                                Intent intent= new Intent(MilkTea.this, ProductDetailActivity.class);
                                intent.putExtra("itemId",model.getItemId());
                                startActivity(intent);
                                finish();
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
//        imgView=(ImageView) findViewById(R.id.img_back);
//        imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iten=new Intent(MilkTea.this, MainActivity.class);
//                startActivity(iten);
//                finish();
//            }
//        });
//        imageV=(ImageView) findViewById(R.id.img_giohang_classs);
//        imageV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iten= new Intent(MilkTea.this, GioHang.class);
//                startActivity(iten);
//                finish();
//            }
//        });
//    }

//    private void filter(String text) {
//        ArrayList<FoodData> filterList= new ArrayList<>();
//        for(FoodData item:mFoodList)
//        {
//            if(item.getItemTen().toLowerCase().contains(text.toLowerCase()))
//            {
//                filterList.add(item);
//            }
//        }
//        myAdapter.filteredList(filterList);
//    }
}
