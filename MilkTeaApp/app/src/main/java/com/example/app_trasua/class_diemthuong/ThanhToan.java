package com.example.app_trasua.class_diemthuong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_trasua.MainActivity;
import com.example.app_trasua.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class ThanhToan extends AppCompatActivity {
    EditText edt_hoten,edt_sdt,edt_diachi,edt_email;
    Button btn_xacnhan;
    TextView txt_ten,txt_gia,txt_soluong,txt_thanhtien,txt_tinhtrang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        anhXa();

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            txt_ten.setText(bundle.getString("Ten"));
            txt_gia.setText(bundle.getString("Gia"));
            txt_soluong.setText(bundle.getString("SoLuong"));
            txt_thanhtien.setText(bundle.getString("ThanhTien"));
        }
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
                Toast.makeText(ThanhToan.this, "Thành công", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(ThanhToan.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void anhXa() {
        btn_xacnhan=(Button) findViewById(R.id.btn_xacnhan);
        edt_hoten=(EditText) findViewById(R.id.edt_hoten);
        edt_sdt=(EditText) findViewById(R.id.edt_sdt);
        edt_diachi=(EditText) findViewById(R.id.edt_diachi);
        edt_email=(EditText) findViewById(R.id.edt_email);
        txt_ten=findViewById(R.id.txt_ten_thanhtoan);
        txt_gia=findViewById(R.id.txt_gia_thanhtoan);
        txt_soluong=findViewById(R.id.txt_sl_thanhtoan);
        txt_thanhtien=(TextView) findViewById(R.id.txt_thanhtien);
        txt_tinhtrang=findViewById(R.id.txt_tinhtrang);
    }

    public void upload(){

        UpLoad_ThanhToan upLoad_thanhToan= new UpLoad_ThanhToan(
                edt_hoten.getText().toString(),
                edt_sdt.getText().toString(),
                edt_diachi.getText().toString(),
                edt_email.getText().toString(),
                txt_ten.getText().toString(),
                txt_gia.getText().toString(),
                txt_soluong.getText().toString(),
                txt_thanhtien.getText().toString(),
                txt_tinhtrang.getText().toString()
        );
        String mCurrentDataTime= DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("DonDatHang").child(mCurrentDataTime)
                .setValue(upLoad_thanhToan).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ThanhToan.this, "  Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }) .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ThanhToan.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
