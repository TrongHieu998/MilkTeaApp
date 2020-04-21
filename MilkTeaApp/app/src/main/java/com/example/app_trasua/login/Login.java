package com.example.app_trasua.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_trasua.Admin.Home_Admin;
import com.example.app_trasua.MainActivity;
import com.example.app_trasua.Model.Users;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;


public class Login extends AppCompatActivity {
    Button btn_dangnhap;
    EditText edt_sdt,edt_matkhau;
    private String parentDbName="Users";
    FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    CheckBox checkBox_nhoDangNhap;
    TextView AdminLink,notAdminLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        loadingBar= new ProgressDialog(this);
        Paper.init(this);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_dangnhap.setText("Đăng nhập Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentDbName="Admins";
            }
        });
        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_dangnhap.setText("Đăng nhập");
                AdminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentDbName="Users";
            }
        });
    }

    private void anhXa() {
        mAuth = FirebaseAuth.getInstance();
        edt_sdt=findViewById(R.id.edt_sdt_login);
        edt_matkhau=findViewById(R.id.edt_matkhau_login);
        btn_dangnhap=findViewById(R.id.btn_dangnhap_login);
        checkBox_nhoDangNhap=findViewById(R.id.checkbox1);
        notAdminLink=findViewById(R.id.not_admin_panel_link);
        AdminLink=findViewById(R.id.admin_panel_link);
    }

    private void LoginUser() {
        String sdt=edt_sdt.getText().toString();
        String matkhau=edt_matkhau.getText().toString();
        if(TextUtils.isEmpty(sdt))
        {
            Toast.makeText(this, "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(matkhau))
        {
            Toast.makeText(this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Đăng nhập");
            loadingBar.setMessage("Vui lòng chờ trong giây lát, chúng tôi đang kiểm tra");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            AllowAcessToAccount(sdt,matkhau);
        }
    }

    private void AllowAcessToAccount(final String sdt, final String matkhau) {
        if (checkBox_nhoDangNhap.isChecked())
        {
            Paper.book().write(Prevalent.UserSDTKey,sdt);
            Paper.book().write(Prevalent.UserMatKhauKey,matkhau);
        }

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(sdt).exists())
                {
                    Users usersData=dataSnapshot.child(parentDbName).child(sdt).getValue(Users.class);
                    if(usersData.getSDT().equals(sdt))
                    {
                        if(usersData.getMatKhau().equals(matkhau))
                        {
                            if(parentDbName.equals("Admins"))
                            {
                                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent= new Intent(Login.this, Home_Admin.class);
                                startActivity(intent);
                            }
                            else if(parentDbName.equals("Users"))
                            {
                                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent= new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }
                    }
                }
                else
                {
                    Toast.makeText(Login.this, sdt+ " không tồn tại", Toast.LENGTH_SHORT).show();
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
