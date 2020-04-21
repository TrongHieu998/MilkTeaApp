package com.example.app_trasua.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_trasua.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DangKi extends AppCompatActivity {
    Button btn_dangki;
    EditText edt_name,edt_sdt,edt_matkhau;
    private ProgressDialog loadingBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        mAuth=FirebaseAuth.getInstance();
        anhXa();
        btn_dangki.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CreateAcount();
            }
        });
    }

    private void CreateAcount() {
        String name=edt_name.getText().toString();
        String sdt=edt_sdt.getText().toString();
        String matkhau=edt_matkhau.getText().toString();
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(sdt))
        {
            Toast.makeText(this, "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(matkhau))
        {
            Toast.makeText(this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Tạo tài khoản");
            loadingBar.setMessage("Vui lòng chờ trong giây lát, chúng tôi đang kiểm tra");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            validatephoneNumber(name,sdt,matkhau);
        }
    }

    private void validatephoneNumber(final String name, final String sdt, final String matkhau) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child("Users").child(sdt).exists())
                {
                    HashMap<String,Object> userdataMap= new HashMap<>();
                    userdataMap.put("SDT",sdt);
                    userdataMap.put("MatKhau",matkhau);
                    userdataMap.put("Ten",name);
                    RootRef.child("Users").child(sdt).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(DangKi.this, "Chúc mừng, bạn đã tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent= new Intent(DangKi.this, main.class);
                                        startActivity(intent);
                                    }
                                    else 
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(DangKi.this, "Lỗi!!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(DangKi.this, sdt+ "đã tồn tại", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(DangKi.this, "Vui lòng thử lại SDT khác", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(DangKi.this, main.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void anhXa() {
        btn_dangki=(Button) findViewById(R.id.btn_dangki_dk);
        edt_name=(EditText) findViewById(R.id.edt_name);
        edt_sdt=(EditText) findViewById(R.id.edt_sdt);
        edt_matkhau=(EditText) findViewById(R.id.edt_matkhau);
        loadingBar= new ProgressDialog(this);
    }
}
