package com.example.app_trasua.class_diemthuong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.app_trasua.MainActivity;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

public class ThongTinCaNhan extends AppCompatActivity {
    EditText edt_ten,edt_sdt,edt_diachi,edt_email;
    Button btn_luu,btn_huy;
    LinearLayout lnl;
    private RecyclerView avartar;
    private Uri uri;
    private String myUri="";
    private String checked="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        edt_ten=findViewById(R.id.edt_ten_ttnd);
        edt_sdt=findViewById(R.id.edt_sdt_ttnd);
        edt_diachi=findViewById(R.id.edt_diachi_ttnd);
        edt_email=findViewById(R.id.edt_email_ttnd);
        btn_luu=findViewById(R.id.btn_luu_ttnd);
        btn_huy=findViewById(R.id.btn_huy_ttnd);
        lnl=findViewById(R.id.img_avatar_hh);
      //  avartar=findViewById(R.id.avatar);
       // UserInfoDisplay(edt_ten,edt_sdt,edt_email,edt_diachi);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt_ten.getText().toString()))
                {
                     Toast.makeText(ThongTinCaNhan.this, "Bạn chưa nhập họ tên", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(edt_sdt.getText().toString()))
                {
                    Toast.makeText(ThongTinCaNhan.this, "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(edt_email.getText().toString()))
                {
                    Toast.makeText(ThongTinCaNhan.this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(edt_diachi.getText().toString()))
                {
                    Toast.makeText(ThongTinCaNhan.this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ThongTinCaNhan.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
//                if(checked.equals("clicked"))
//                {
//                    userInfoSave();
//                }
//                else
//                {
//                    userUpdate();
//                }
            }
        });
    }
    public void img_avatar_click(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            uri=data.getData();
            //lnl.setImageURI(uri);
        }
        else
        {
            Toast.makeText(this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
        }
    }


    //    private void userUpdate() {
//        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
//        HashMap<String,Object> hashMap= new HashMap<>();
//        hashMap.put("itemTen",edt_ten.getText().toString());
//        hashMap.put("itemSDT",edt_sdt.getText().toString());
//        hashMap.put("itemEmail",edt_email.getText().toString());
//        hashMap.put("itemDiachi",edt_diachi.getText().toString());
//        startActivity(new Intent(ThongTinCaNhan.this, MainActivity.class));
//        Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
//        finish();
//    }

//    private void userInfoSave() {
//        if(TextUtils.isEmpty(edt_ten.getText().toString()))
//        {
//            Toast.makeText(this, "Bạn chưa nhập họ tên", Toast.LENGTH_SHORT).show();
//        }
//        else if(TextUtils.isEmpty(edt_sdt.getText().toString()))
//        {
//            Toast.makeText(this, "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
//        }
//        else if(TextUtils.isEmpty(edt_email.getText().toString()))
//        {
//            Toast.makeText(this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
//        }
//        else if(TextUtils.isEmpty(edt_diachi.getText().toString()))
//        {
//            Toast.makeText(this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
//        }
//        else if(checked.equals("licked"))
//        {
//            upLoadImg();
//        }

//    }

//    private void upLoadImg() {
//    }

//    private void UserInfoDisplay(final EditText edt_ten, final EditText edt_sdt, final EditText edt_email, final EditText edt_diachi)
//    {
//        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getSDT());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    if(dataSnapshot.child("itemAvatar").exists())
//                    {
//                       // String itemAvatar=dataSnapshot.child("itemAvatar").getValue().toString();
//                        String itemHoTen=dataSnapshot.child("itemHoTen").getValue().toString();
//                        String itemSDT=dataSnapshot.child("itemSDT").getValue().toString();
//                        String itemEmail=dataSnapshot.child("itemEmail").getValue().toString();
//                        String itemDiachi=dataSnapshot.child("itemDiachi").getValue().toString();
//                        //Picasso.get().load(itemAvatar).into(avartar);
//                        edt_ten.setText(itemHoTen);
//                        edt_sdt.setText(itemSDT);
//                        edt_diachi.setText(itemDiachi);
//                        edt_email.setText(itemEmail);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

}
