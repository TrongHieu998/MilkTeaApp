package com.example.app_trasua.Single_Code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_trasua.Admin.Apdapter_Admin;
import com.example.app_trasua.MainActivity;
import com.example.app_trasua.Model.Apdapter_Cart;
import com.example.app_trasua.Model.Cart;
import com.example.app_trasua.Model.CartDetail;
import com.example.app_trasua.Model.Confirm;
import com.example.app_trasua.Model.Products;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.FoodData;
import com.example.app_trasua.class_home.MilkTea;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinal extends AppCompatActivity {
    private EditText edt_hoten, edt_sdt, edt_diachi, edt_email;
    private Button btn_xacnhan;
    private String tongtien = "";
    private RecyclerView recyclerView;
    Confirm confirm;
    ArrayList<CartDetail> arrayList;
    Apdapter_Cart myAdapter;
    String tinhtrang="Chưa giao hàng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final);
        recyclerView = findViewById(R.id.recycleView_confirm);
        tongtien = getIntent().getStringExtra("Tong Tien");
        Toast.makeText(this, "Tong Tien =" + tongtien, Toast.LENGTH_SHORT).show();

        arrayList = new ArrayList<>();
        myAdapter = new Apdapter_Cart(ConfirmFinal.this, arrayList);
        btn_xacnhan = (Button) findViewById(R.id.btn_confirm);
        edt_hoten = (EditText) findViewById(R.id.edt_hoten_confirm);
        edt_sdt = (EditText) findViewById(R.id.edt_sdt_confirm);
        edt_diachi = (EditText) findViewById(R.id.edt_diachi_confirm);
        edt_email = (EditText) findViewById(R.id.edt_email_confirm);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ConfirmFinal.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        //db= FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea");
        // getConfirm(ID);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }


    private void check() {
        if (TextUtils.isEmpty(edt_hoten.getText().toString())) {
            Toast.makeText(this, "Bạn chưa nhập họ tên", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edt_email.getText().toString())) {
            Toast.makeText(this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edt_diachi.getText().toString())) {
            Toast.makeText(this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edt_sdt.getText().toString())) {
            Toast.makeText(this, "Bạn chưa nhập SDT", Toast.LENGTH_SHORT).show();
        } else {
            ConfirmOder();
        }
    }

    private void ConfirmOder() {
        final String mCurrentDataTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        //    arrayList= new ArrayList<>();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("GioHang").child("User View")
                .child(Prevalent.currentOnlineUser.getSDT()).child("SanPham");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CartDetail cartDetail = snapshot.getValue(CartDetail.class);
                    arrayList.add(cartDetail);
                    confirm = new Confirm(
                            edt_hoten.getText().toString(),
                            edt_sdt.getText().toString(),
                            edt_email.getText().toString(),
                            edt_diachi.getText().toString(),
                            tinhtrang,
                            tongtien,
                            arrayList);
                    databaseReference.child(Prevalent.currentOnlineUser.getSDT()).child(mCurrentDataTime).setValue(confirm);
                    //databaseReference.child(Prevalent.currentOnlineUser.getSDT()).child(mCurrentDataTime).setValue(confirm);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference().child("GioHang")
                .child("User View")
                .child(Prevalent.currentOnlineUser.getSDT())
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ConfirmFinal.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ConfirmFinal.this, MainActivity.class);
                          //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            arrayList.clear();
                            finish();
                        }
                    }
                });

        // .child(ID);
//        HashMap<String,Object> orderMap= new HashMap<>();
//        orderMap.put("itemTongtien",tongtien);
//        orderMap.put("itemHoTen",edt_hoten.getText().toString());
//        orderMap.put("itemSDT",edt_sdt.getText().toString());
//        orderMap.put("itemEmail",edt_email.getText().toString());
//        orderMap.put("itemDiachi",edt_diachi.getText().toString());
//        orderMap.put("itemTinhtrang","Chưa giao hàng");
    }
}

//        db.child(Prevalent.currentOnlineUser.getSDT())
//                .child(ID)
//                .updateChildren(orderMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful())
//                        {
//                            FirebaseDatabase.getInstance().getReference().child("GioHang")
//                            .child("User View")
//                            .child(Prevalent.currentOnlineUser.getSDT())
//                            .removeValue()
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful())
//                                    {
//                                        Toast.makeText(ConfirmFinal.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
//                                        Intent intent= new Intent(ConfirmFinal.this, MainActivity.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//                                }
//                            });
//                        }
//                    }
//                });

//    private void getConfirm(final String ID) {
//        db.child(ID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                if(dataSnapshot.exists())
//                {
//
//                    Products products=dataSnapshot.getValue(Products.class);
////                    txt_ten.setText(products.getItemTen());
////                    txt_gia.setText(products.getItemGia());
////                    txt_chitiet.setText(products.getItemChitiet());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
 //   }

