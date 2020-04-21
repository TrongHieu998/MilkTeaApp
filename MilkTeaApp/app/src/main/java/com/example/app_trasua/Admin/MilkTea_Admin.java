package com.example.app_trasua.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.app_trasua.MainActivity;
import com.example.app_trasua.R;
import com.example.app_trasua.class_diemthuong.GioHang;
import com.example.app_trasua.class_home.Adapter_Milk_Tea;
import com.example.app_trasua.class_home.FoodData;
import com.example.app_trasua.class_home.MilkTea;
import com.example.app_trasua.login.Login;
import com.example.app_trasua.login.main;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MilkTea_Admin extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<FoodData> mFoodList;
    FoodData mFoodData;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    ImageView imgView,image_dangxuat;
    EditText edt_timkiem;
    Apdapter_Admin myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk_tea_admin);
        image_dangxuat=findViewById(R.id.img_dangxuat_admin);
        mRecyclerView=findViewById(R.id.recycleView_admin);
        edt_timkiem=(EditText) findViewById(R.id.edt_timkiem_admin);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(MilkTea_Admin.this,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        mFoodList= new ArrayList<>();
        myAdapter = new Apdapter_Admin(MilkTea_Admin.this,mFoodList);
        mRecyclerView.setAdapter(myAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea");
        progressDialog.show();
        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mFoodList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    FoodData foodData= snapshot.getValue(FoodData.class);
                    // Chỗ này là xóa
                    foodData.setKey(snapshot.getKey());
                    //Chõ này là xóa
                    mFoodList.add(foodData);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        edt_timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        imgView=(ImageView) findViewById(R.id.img_back_admin);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iten=new Intent(MilkTea_Admin.this, Home_Admin.class);
                startActivity(iten);
                finish();
            }
        });
        image_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent= new Intent(MilkTea_Admin.this, main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
    private void filter(String text) {
        ArrayList<FoodData> filterList= new ArrayList<>();
        for(FoodData item:mFoodList)
        {
            if(item.getItemTen().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }
        myAdapter.filteredList(filterList);
    }

    public void btn_up_admin(View view) {
        startActivity(new Intent(this, UpLoad.class));
    }
}
