package com.example.app_trasua.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_trasua.Admin.AdminNewOrderActivity;
import com.example.app_trasua.Admin.MilkTea_Admin;
import com.example.app_trasua.class_diemthuong.DonHangcCuaToiActivity;
import com.example.app_trasua.class_diemthuong.ThongTinCaNhan;
import com.example.app_trasua.login.Login;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.FreshFruitTea;
import com.example.app_trasua.login.main;

import io.paperdb.Paper;


public class FragmentNguoiDung extends Fragment implements View.OnClickListener {
    ImageView imgV_ND1;
    ImageView imgV_ND2;
    ImageView imgV_ND3;
    ImageView imgV_ND4;
    View view;
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        view=inflater.inflate(R.layout.fragmennt_nguoidung, null);
        imgV_ND1=(ImageView) view.findViewById(R.id.imgV_ND1);
        imgV_ND2=(ImageView) view.findViewById(R.id.imgV_ND2);
        imgV_ND3=(ImageView) view.findViewById(R.id.imgV_ND3);
        imgV_ND4=(ImageView) view.findViewById(R.id.imgV_ND4);
        imgV_ND1.setOnClickListener(this);
        imgV_ND2.setOnClickListener(this);
        imgV_ND3.setOnClickListener(this);
        imgV_ND4.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.imgV_ND1:{
                Intent intent= new Intent(getActivity(), AdminNewOrderActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.imgV_ND2:{
                Intent intent= new Intent(getActivity(), ThongTinCaNhan.class);
                startActivity(intent);
                break;
            }
            case R.id.imgV_ND3:{
                Intent intent= new Intent(getActivity(), FreshFruitTea.class);
                startActivity(intent);
                break;
            }
            case R.id.imgV_ND4:{
                Paper.book().destroy();
                Intent intent= new Intent(getActivity(), main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
        }

    }
}
