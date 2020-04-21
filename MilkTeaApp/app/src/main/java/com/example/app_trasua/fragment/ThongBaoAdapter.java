package com.example.app_trasua.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_trasua.R;

import java.util.List;

public class ThongBaoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThongBao> thongBaoList;

    public ThongBaoAdapter(Context context, int layout, List<ThongBao> thongBaoList) {
        this.context = context;
        this.layout = layout;
        this.thongBaoList = thongBaoList;
    }
    @Override
    public int getCount() {
        return thongBaoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);
        // ánh xạ
        TextView txtMOTA= (TextView) view.findViewById(R.id.txtMOTA);
        TextView txtNgay= (TextView) view.findViewById(R.id.txtNgay);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.imgAnh);
        // gán giá trị
        ThongBao tb=thongBaoList.get(i);
        txtMOTA.setText(tb.getMoTa());
        txtNgay.setText(tb.getNgay());
        imgHinh.setImageResource(tb.getHinhAnh());
        return view;
    }
}
