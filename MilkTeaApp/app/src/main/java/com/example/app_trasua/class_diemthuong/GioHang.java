package com.example.app_trasua.class_diemthuong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app_trasua.Admin.UpDate;
import com.example.app_trasua.MainActivity;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.Adapter_Milk_Tea;
import com.example.app_trasua.class_home.FoodData;
import com.example.app_trasua.class_home.Macchiato;
import com.example.app_trasua.fragment.FragmentHome;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GioHang extends AppCompatActivity {
    Button btn_thanhtoan,btn_tieptucmuahang;
    ImageView img_back,img_giohang;
    TextView txt_tongtien,txt_soluong,txt_ten,txt_gia;
    String imageUrl;
    RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();
//        GridLayoutManager gridLayoutManager= new GridLayoutManager(GioHang.this,1);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        progressDialog= new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//
//        mGioHangList= new ArrayList<>();
//        final AdapterGioHang myAdapter = new AdapterGioHang(GioHang.this,mGioHangList);
//        recyclerView.setAdapter(myAdapter);
//        databaseReference= FirebaseDatabase.getInstance().getReference("GioHang");
//        progressDialog.show();
//        eventListener= databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mGioHangList.clear();
//                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    GioHangList gioHangList= snapshot.getValue(GioHangList.class);
//                    mGioHangList.add(gioHangList);
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
        Bundle mBundle= getIntent().getExtras();
            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(img_giohang);
            txt_ten.setText(mBundle.getString("Ten"));
            txt_gia.setText(mBundle.getString("Gia"));
            txt_soluong.setText(mBundle.getString("Soluong"));
            txt_tongtien.setText(mBundle.getString("TongTien"));
            imageUrl=mBundle.getString("Image");
        img_back=(ImageView) findViewById(R.id.img_back_giohang);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ThanhToan.class)
                        .putExtra("Ten",txt_ten.getText().toString())
                        .putExtra("SoLuong",txt_soluong.getText().toString())
                        .putExtra("Gia",txt_gia.getText().toString())
                        .putExtra("ThanhTien",txt_tongtien.getText().toString())
                );
            }
        });
//        btn_tieptucmuahang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(GioHang.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    private void anhXa() {
       // btn_tieptucmuahang=(Button) findViewById(R.id.btn_tieptucmuahang);
        btn_thanhtoan=(Button) findViewById(R.id.btn_thanhtoan);
        txt_tongtien=(TextView) findViewById(R.id.txt_tongtien);
        txt_soluong=(TextView) findViewById(R.id.txt_soluong_dongGioHang);
        txt_ten=(TextView) findViewById(R.id.txt_ten_dongGioHang);
        txt_gia=(TextView) findViewById(R.id.txt_gia_dongGioHang);
        img_giohang=(ImageView) findViewById(R.id.img_dongGioHang);
//        recyclerView=(RecyclerView) findViewById(R.id.recycleView_giohang);
    }

}
