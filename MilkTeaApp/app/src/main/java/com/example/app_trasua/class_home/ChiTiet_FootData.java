package com.example.app_trasua.class_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.app_trasua.Admin.UpDate;
import com.example.app_trasua.R;
import com.example.app_trasua.class_diemthuong.GioHang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChiTiet_FootData extends AppCompatActivity {
    TextView txt_ten,txt_chitiet,txt_gia;
    ImageView img_anh,img_close;
    ElegantNumberButton numberButton;
    Button btn_adđGioHang;
    String key="";
    String imageURL="";
    double tt=1.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_foot_data);
        txt_chitiet=(TextView) findViewById(R.id.txt_chitiet);
        txt_ten=(TextView) findViewById(R.id.txt_chitiet_tensp);
        txt_gia=(TextView) findViewById(R.id.txt_chitiet_gia);
        img_anh=(ImageView) findViewById(R.id.img_chitiet_anh);
        img_close=(ImageView) findViewById(R.id.img_close);
        btn_adđGioHang=(Button) findViewById(R.id.btn_chitiet_themvaogio);
        numberButton=(ElegantNumberButton) findViewById(R.id.numberButton_chitiet);
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
        btn_adđGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChiTiet_FootData.this,GioHang.class);
                //Picasso.get().load(mFoodList.get(position).getItemImg()).into(holder.imageView);
                intent.putExtra("Image",imageURL);
                intent.putExtra("Ten",txt_ten.getText().toString());
                intent.putExtra("Gia",txt_gia.getText().toString());
                String soluong=numberButton.getNumber();
                intent.putExtra("Soluong",soluong);
                double tam=((Double.valueOf(txt_gia.getText().toString())));
                tt=tam*(Integer.valueOf(numberButton.getNumber()));
                String g=String.valueOf(tt);
                intent.putExtra("TongTien",g);
                startActivity(intent);
            }
        });

    }

//    public void btn_Delete(View view) {
//        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("SanPham").child("Special Drink");
//        FirebaseStorage storage=FirebaseStorage.getInstance();
//        StorageReference storageReference=storage.getReferenceFromUrl(imageURL);
//        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                reference.child(key).removeValue();
//                Toast.makeText(ChiTiet_FootData.this, "Deleted", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(),Special_Drink.class));
//                finish();
//            }
//        });
//    }

//    public void btn_update(View view) {
//        startActivity(new Intent(getApplicationContext(), UpDate.class)
//                .putExtra("TenKey",txt_ten.getText().toString())
//                .putExtra("GiaKey",txt_gia.getText().toString())
//                .putExtra("ChiTietKey",txt_chitiet.getText().toString())
//                .putExtra("Image",imageURL)
//                .putExtra("keyValue",key)
//        );
//    }
}
