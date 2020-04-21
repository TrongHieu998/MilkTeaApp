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

import com.example.app_trasua.login.Login;
import com.example.app_trasua.R;
import com.example.app_trasua.class_diemthuong.TichLuyDiemThuong;
import com.example.app_trasua.class_home.FreshFruitTea;


public class FragmentDiemThuong extends Fragment implements View.OnClickListener {
    ImageView imgV_DT1;
    ImageView imgV_DT2;
    ImageView imgV_DT3;
    ImageView imgV_DT4;
    View view;
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        view=inflater.inflate(R.layout.fragmennt_diemthuong, null);
        imgV_DT1=(ImageView) view.findViewById(R.id.imgV_DT1);
        imgV_DT2=(ImageView) view.findViewById(R.id.imgV_DT2);
        imgV_DT3=(ImageView) view.findViewById(R.id.imgV_DT3);
        imgV_DT4=(ImageView) view.findViewById(R.id.imgV_DT4);
        imgV_DT1.setOnClickListener(this);
        imgV_DT2.setOnClickListener(this);
        imgV_DT3.setOnClickListener(this);
        imgV_DT4.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgV_DT1: {
                Intent intent = new Intent(getActivity(), FreshFruitTea.class);
                startActivity(intent);
                break;
            }
            case R.id.imgV_DT2: {
                Intent intent = new Intent(getActivity(), TichLuyDiemThuong.class);
                startActivity(intent);
                break;
            }
            case R.id.imgV_DT3: {
                Intent intent = new Intent(getActivity(), FreshFruitTea.class);
                startActivity(intent);
                break;
            }
            case R.id.imgV_DT4: {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                break;
            }
        }
    }
}
