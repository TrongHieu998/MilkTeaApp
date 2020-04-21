package com.example.app_trasua.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_trasua.R;
import com.example.app_trasua.class_home.FoodData;
import com.example.app_trasua.class_home.MilkTea;
import com.example.app_trasua.class_home.Special_Drink;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UpLoad extends AppCompatActivity {
    private String itemChitiet,itemGia,itemTen, date,time,randomkey,downloadImg;
    ImageView chonAnh;
    Uri uri;
    String imageUri;
    EditText edt_ten,edt_gia,edt_chitiet;
    Button btn_add;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private static final int h=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);
        storageReference = FirebaseStorage.getInstance().getReference().child("LinkImage");
        databaseReference = FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea");
        chonAnh = (ImageView) findViewById(R.id.iv_milkteaImage);
        edt_ten = (EditText) findViewById(R.id.edt_tentrasua);
        edt_gia = (EditText) findViewById(R.id.edt_gia);
        edt_chitiet = (EditText) findViewById(R.id.edt_chitiet);
        btn_add = findViewById(R.id.btn_save);
//       chonAnh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validateProductData();
//            }
//        });
//    }

//    private void openGallery() {
//        Intent intent=new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent,h);
//    }
    }
        public void btn_chonAnh (View view){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && requestCode == h && data != null) {
                uri = data.getData();
                chonAnh.setImageURI(uri);
            } else {
                Toast.makeText(this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
            }
        }
//    private void validateProductData() {
//        itemChitiet=edt_chitiet.getText().toString();
//        itemGia=edt_gia.getText().toString();
//        itemTen=edt_ten.getText().toString();
//        if(TextUtils.isEmpty(itemTen))
//        {
//            Toast.makeText(this, "Bạn chưa nhập tên trà sữa", Toast.LENGTH_SHORT).show();
//        }
//        else if(TextUtils.isEmpty(itemGia))
//        {
//            Toast.makeText(this, "Bạn chưa nhập giá", Toast.LENGTH_SHORT).show();
//        }
//        else if(TextUtils.isEmpty(itemChitiet))
//        {
//            Toast.makeText(this, "Bạn chưa nhập mô tả chi tiết", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//           stroreProduct();
//        }
//
//    }

//    private void stroreProduct() {
//        Calendar calendar=Calendar.getInstance();
//        SimpleDateFormat currentDate= new SimpleDateFormat("MMM dd, yyyy");
//        date=currentDate.format(calendar.getTime());
//        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
//        time=currentTime.format(calendar.getTime());
//        randomkey=date + time;
//        final StorageReference filePath=storageReference.child(uri.getLastPathSegment() +randomkey + ".jpg");
//        final UploadTask uploadTask=filePath.putFile(uri);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                String message=e.toString();
//                Toast.makeText(UpLoad.this, "Lỗi", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(UpLoad.this, "Thành công", Toast.LENGTH_SHORT).show();
//                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                    @Override
//                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                        if (!task.isSuccessful())
//                        {
//                            throw  task.getException();
//                        }
//                        downloadImg=filePath.getDownloadUrl().toString();
//                        return filePath.getDownloadUrl();
//                    }
//                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        if(task.isSuccessful())
//                        {
//                            Toast.makeText(UpLoad.this, "Thành công", Toast.LENGTH_SHORT).show();
//                            SaveProduct();
//                        }
//                    }
//                });
//
//            }
//        });
//    }

//    private void SaveProduct() {
//        HashMap<String,Object> productMap= new HashMap<>();
//        productMap.put("itemId",randomkey);
//        productMap.put("itemTen",itemTen);
//        productMap.put("itemGia",itemGia);
//        productMap.put("itemChitiet",itemChitiet);
//        productMap.put("itemImg",downloadImg);
//        databaseReference.child(randomkey).updateChildren(productMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful())
//                        {
//                            Intent intent= new Intent(UpLoad.this, MilkTea.class);
//                            startActivity(intent);
//                            Toast.makeText(UpLoad.this, "Thành công", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            String message=task.getException().toString();
//                            Toast.makeText(UpLoad.this, "Lỗi" +message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }


    public void uploadImage(){
        StorageReference storageReference= FirebaseStorage.getInstance().getReference()
                .child("LinkImage").child(uri.getLastPathSegment());
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage=uriTask.getResult();
                imageUri =urlImage.toString();
                upload();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }
    public void btn_upload(View view) {
        uploadImage();
    }
    public void upload(){
        String mCurrentDataTime= DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FoodData foodData= new FoodData(
                edt_ten.getText().toString(),
                edt_gia.getText().toString(),
                imageUri,
                edt_chitiet.getText().toString(),
                mCurrentDataTime
        );

        FirebaseDatabase.getInstance().getReference("SanPham").child("Milk Tea").child(mCurrentDataTime)
                .setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful())
                 {
                     Toast.makeText(UpLoad.this, "  Uploaded", Toast.LENGTH_SHORT).show();
                     finish();
                 }
            }
        }) .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpLoad.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
