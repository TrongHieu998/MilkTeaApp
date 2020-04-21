package com.example.app_trasua.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_trasua.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class Main_Load extends AppCompatActivity {
    Button btn_chonAnh, btn_luuAnh;
    ImageView ivAnh;
    Uri filePath;
    int REQUEST_CODE_IMAGE=1;
    FirebaseStorage storage=FirebaseStorage.getInstance();
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_load);

        storageReference=storage.getReference();

        btn_chonAnh=(Button) findViewById(R.id.btn_chonImg);
        btn_luuAnh=(Button)findViewById(R.id.btn_LuuImg);
        ivAnh=(ImageView) findViewById(R.id.iv_anh);

        ivAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });
        btn_chonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonAnh();
            }
        });
        btn_luuAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luuAnh();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
//            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
//            ivAnh.setImageBitmap(bitmap);
            filePath=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                ivAnh.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void chonAnh() {
        Intent intent= new Intent();
        intent.setType("Img_LoaiSanPham/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),REQUEST_CODE_IMAGE);
    }

    private void luuAnh() {
        if(filePath!=null)
        {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading....");
            progressDialog.show();
            StorageReference reference= storageReference.child("Img_LoaiSanPham/*"+ UUID.randomUUID().toString());
            reference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(Main_Load.this, "Image Upload Success",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progres=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded"+(int)progres+"%");
                }
            });

        }
        else
        {

        }
    }


}
