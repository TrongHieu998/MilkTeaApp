package com.example.app_trasua.Single_Code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.app_trasua.Model.Products;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.MilkTea;
import com.example.app_trasua.class_home.Special_Drink;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddDetail_Special extends AppCompatActivity {
    private Button btn_addToCard;
    private ImageView img_detail,img_close;
    private ElegantNumberButton numberButton;
    private TextView txt_ten,txt_gia,txt_chitiet;
    public String productID="";
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productID=getIntent().getStringExtra("itemId");
        btn_addToCard=findViewById(R.id.btn_chitiet_adđCard);
        numberButton= findViewById(R.id.numberButton_detail);
        txt_ten=findViewById(R.id.txt_name_detail);
        txt_gia=findViewById(R.id.txt_gia_deatail);
        txt_chitiet=findViewById(R.id.txt_chitiet_detail);
        img_detail=findViewById(R.id.img_detail);
        img_close=findViewById(R.id.img_close_detail);
        databaseReference= FirebaseDatabase.getInstance().getReference("SanPham").child("Special Drink");
        getProductDetails(productID);
        btn_addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCardList();
            }
        });
    }

    private void addToCardList() {
        String date,time;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd, yyyy");
        date=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        time=currentTime.format(calendar.getTime());
        final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("GioHang");
        final HashMap<String,Object> cardMap= new HashMap<>();
        cardMap.put("itemId",productID);
        cardMap.put("itemTen",txt_ten.getText().toString());
        cardMap.put("itemGia",txt_gia.getText().toString());
        cardMap.put("itemSoluong",numberButton.getNumber());

        databaseReference.child("User View").child(Prevalent.currentOnlineUser.getSDT())
                .child("SanPham").child(productID)
                .updateChildren(cardMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            databaseReference.child("Admin View").child(Prevalent.currentOnlineUser.getSDT())
                                    .child("SanPham").child(productID)
                                    .updateChildren(cardMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(AddDetail_Special.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                                Intent intent= new Intent(AddDetail_Special.this, Special_Drink.class);
                                                finish();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
    private void getProductDetails(String productID) {
        databaseReference.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    Products products=dataSnapshot.getValue(Products.class);
                    txt_ten.setText(products.getItemTen());
                    txt_gia.setText(products.getItemGia());
                    Picasso.get().load(products.getItemImg()).into(img_detail);
                    txt_chitiet.setText(products.getItemChitiet());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
