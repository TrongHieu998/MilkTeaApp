package com.example.app_trasua.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_trasua.R;

import java.util.ArrayList;

public class FragmentThongBao extends Fragment {
    ListView lvThongBao;
    ArrayList <ThongBao> arrayThongBao;
    ThongBaoAdapter adapter;
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        view=inflater.inflate(R.layout.fragmennt_thongbao, null);
        anhXa();
        adapter= new ThongBaoAdapter(view.getContext(),R.layout.dong_thong_bao,arrayThongBao);
        lvThongBao.setAdapter(adapter);
        return view;

    }
    private void anhXa() {
        lvThongBao= (ListView) view.findViewById(R.id.list);
        arrayThongBao = new ArrayList<>();
        arrayThongBao.add(new ThongBao("Giảm 50%","27/10/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 30%","20/10/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 45%","13/10/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 30%","07/10/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 50%","31/09/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 40%","25/09/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 20%","18/09/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 150%","11/09/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 35%","04/09/2019",R.drawable.skt));
        arrayThongBao.add(new ThongBao("Giảm 50%","27/08/2019",R.drawable.skt));
    }
}
