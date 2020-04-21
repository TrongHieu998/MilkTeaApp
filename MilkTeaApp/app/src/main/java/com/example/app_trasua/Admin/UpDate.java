package com.example.app_trasua.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.FoodData;
import com.example.app_trasua.class_home.MilkTea;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UpDate extends AppCompatActivity {
    ImageView imgAnh;
    Uri uri;
    String imageURL;
    EditText edt_ten,edt_gia,edt_chitiet;
    Button btn_update;
    String key;
    private  DatabaseReference databaseReference;
    StorageReference storageReference;
    String ten,chitiet,gia;
    public String productID="";
    String mCurrentDataTime= DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
//
//        productID=getIntent().getStringExtra("itemId");
//        databaseReference=FirebaseDatabase.getInstance().getReference().child("SanPham").child(productID);

        btn_update=findViewById(R.id.btn_update);
        imgAnh=(ImageView) findViewById(R.id.imageView_update);
        edt_ten=(EditText) findViewById(R.id.edt_tentrasua_update);
        edt_chitiet=(EditText)findViewById(R.id.edt_chitiet_update);
        edt_gia=(EditText) findViewById(R.id.edt_gia_update);
//
//        Displayproduct();
//        btn_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                applyChanges();
//            }
//        });
//
//
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            Glide.with(UpDate.this)
                    .load(bundle.getString("Image"))
                    .into(imgAnh);
            edt_ten.setText(bundle.getString("TenKey"));
            edt_gia.setText(bundle.getString("GiaKey"));
            edt_chitiet.setText(bundle.getString("ChiTietKey"));
            imageURL=bundle.getString("Image");
            key=bundle.getString("keyValue");

        }
        databaseReference= FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea").child(mCurrentDataTime).child(key);
    }

//    private void applyChanges() {
//        String itemTen=edt_ten.getText().toString();
//        String itemGia=edt_gia.getText().toString();
//        String itemChitiet=edt_chitiet.getText().toString();
//        if(itemTen.equals(""))
//        {
//            Toast.makeText(this, "Bạn chưa nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
//        }
//        else if(itemGia.equals(""))
//        {
//            Toast.makeText(this, "Bạn chưa nhập giá sản phẩm", Toast.LENGTH_SHORT).show();
//        }
//        else if(itemChitiet.equals(""))
//        {
//            Toast.makeText(this, "Bạn chưa nhập mô tả sản phẩm", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            HashMap<String,Object> hashMap= new HashMap<>();
//            hashMap.put("itemId",productID);
//            hashMap.put("itemTen",edt_ten);
//            hashMap.put("itemGia",edt_gia);
//            hashMap.put("itemChitiet",edt_chitiet);
//            databaseReference.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful())
//                    {
//                        Toast.makeText(UpDate.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent= new Intent(UpDate.this, MilkTea.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            });
//        }
//    }

//    private void Displayproduct() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    String itemTen=dataSnapshot.child("itemTen").getValue().toString();
//                    String itemGia=dataSnapshot.child("itemGia").getValue().toString();
//                    String itemChitiet=dataSnapshot.child("itemChitiet").getValue().toString();
//                    String itemImg=dataSnapshot.child("itemImg").getValue().toString();
//
//                    edt_ten.setText(itemTen);
//                    edt_gia.setText(itemGia);
//                    edt_chitiet.setText(itemChitiet);
//                    Picasso.get().load(itemImg).into(imgAnh);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    public void btn_chonAnh_update(View view) {
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
            imgAnh.setImageURI(uri);
        }
        else
        {
            Toast.makeText(this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
        }
    }
   public void btn_update(View view) {
        ten=edt_ten.getText().toString().trim();
        gia= edt_gia.getText().toString().trim();
        chitiet=edt_chitiet.getText().toString().trim();

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Update...");
        progressDialog.show();
        storageReference=FirebaseStorage.getInstance().getReference().child("LinkImage").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage=uriTask.getResult();
                imageURL =urlImage.toString();
                update();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }
    public void update(){
        FoodData foodData= new FoodData(
                ten,
                gia,
                imageURL,
                chitiet,
                mCurrentDataTime
        );
        databaseReference.setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StorageReference storageReference=FirebaseStorage.getInstance().getReferenceFromUrl(imageURL);
                storageReference.delete();
                Toast.makeText(UpDate.this, "Data Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
