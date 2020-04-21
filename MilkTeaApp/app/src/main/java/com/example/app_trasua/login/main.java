package com.example.app_trasua.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_trasua.MainActivity;
import com.example.app_trasua.Model.Users;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class main extends AppCompatActivity {
    Button btn_dangnhap,btn_dangki;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_dangnhap=findViewById(R.id.btn_dangnhap_main);
        btn_dangki=findViewById(R.id.btn_dangki_main);
        loadingBar= new ProgressDialog(this);
        Paper.init(this);

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(main.this, Login.class);
                startActivity(intent);
            }
        });
        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(main.this, DangKi.class);
                startActivity(intent);
            }
        });

        String UserSDTKey=Paper.book().read(Prevalent.UserSDTKey);
        String UserMatKhauKey=Paper.book().read(Prevalent.UserMatKhauKey);
        if(UserSDTKey !="" && UserMatKhauKey !="")
        {
            if(!TextUtils.isEmpty(UserSDTKey)&& !TextUtils.isEmpty(UserMatKhauKey))
            {
                AllowAccess(UserSDTKey,UserMatKhauKey);
                loadingBar.setTitle("Đang đăng nhập");
                loadingBar.setMessage("Vui lòng chờ ....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }
    private void AllowAccess(final String sdt,final String matkhau) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(sdt).exists())
                {
                    Users usersData=dataSnapshot.child("Users").child(sdt).getValue(Users.class);
                    if(usersData.getSDT().equals(sdt))
                    {
                        if(usersData.getMatKhau().equals(matkhau))
                        {
                            Toast.makeText(main.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent= new Intent(main.this, MainActivity.class);
                            Prevalent.currentOnlineUser=usersData;
                            startActivity(intent);
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(main.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(main.this, sdt+ "Không tồn tại", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    // Toast.makeText(Login.this, "Bạn hãy tạo 1 tài khoản mới", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
