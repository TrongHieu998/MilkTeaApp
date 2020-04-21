package com.example.app_trasua.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.app_trasua.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChiTiet_Admin extends AppCompatActivity {
    TextView txt_ten,txt_chitiet,txt_gia;
    ImageView img_anh,img_close;
    ElegantNumberButton numberButton;
    String key="";
    String imageURL="";
    double tt=1.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_admin);
        txt_chitiet=(TextView) findViewById(R.id.txt_chitiet_admin);
        txt_ten=(TextView) findViewById(R.id.txt_chitietten_admin);
        txt_gia=(TextView) findViewById(R.id.txt_chitietgia_admin);
        img_anh=(ImageView) findViewById(R.id.img_chitiet_admin);
        img_close=(ImageView) findViewById(R.id.img_close_admin);
        numberButton=(ElegantNumberButton) findViewById(R.id.numberButton_chitiet_admin);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            Glide.with(this)
                    .load(bundle.getString("Image"))
                    .into(img_anh);
            txt_ten.setText(bundle.getString("Ten"));
            txt_gia.setText(bundle.getString("Gia"));
            txt_chitiet.setText(bundle.getString("ChiTiet"));
            //Chõ này là xóa
            key=bundle.getString("keyValue");
            imageURL=bundle.getString("Image");
            //Chõ này là xóa
        }
    }

    public void btn_Update_admin(View view) {
        startActivity(new Intent(getApplicationContext(), UpDate.class)
                .putExtra("TenKey",txt_ten.getText().toString())
                .putExtra("GiaKey",txt_gia.getText().toString())
                .putExtra("ChiTietKey",txt_chitiet.getText().toString())
                .putExtra("Image",imageURL)
                .putExtra("keyValue",key)
        );
    }
    public void btn_Delete_admin(View view) {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea");
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference storageReference=storage.getReferenceFromUrl(imageURL);
        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                reference.child(key).removeValue();
                Toast.makeText(ChiTiet_Admin.this, "Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MilkTea_Admin.class));
                finish();
            }
        });
    }
}
