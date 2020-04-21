package com.example.app_trasua.class_diemthuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.app_trasua.R;
import com.example.app_trasua.fragment.FragmentDiemThuong;

public class TichLuyDiemThuong extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tich_luy_diem_thuong);
        imageView=(ImageView) findViewById(R.id.img_back_tichluyDT);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                //Intent intent= new Intent(TichLuyDiemThuong.this, FragmentDiemThuong.class);
                //startActivity(intent);
                //finish();
            }
        });
    }
}
