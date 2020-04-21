package com.example.app_trasua.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_trasua.Model.Cart;
import com.example.app_trasua.R;
import com.example.app_trasua.Single_Code.CartActivity;
import com.example.app_trasua.class_home.Che;
import com.example.app_trasua.class_home.MilkTea;
import com.example.app_trasua.class_home.Macchiato;
import com.example.app_trasua.class_home.FreshFruitTea;
import com.example.app_trasua.class_home.Special_Drink;

public class FragmentHome extends Fragment implements View.OnClickListener {
    View view;
    Button btn_fresh,btn_machiato,btn_milktea,btn_special,btn_che;
    ImageView img_giohang;
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        view= inflater.inflate(R.layout.fragmennt_home,null);
        btn_fresh=(Button) view.findViewById(R.id.fresh);
        btn_machiato=(Button) view.findViewById(R.id.macchiato) ;
        btn_milktea=(Button) view.findViewById(R.id.milktea);
        btn_special=(Button) view.findViewById(R.id.special);
        btn_che=(Button) view.findViewById(R.id.che);
        img_giohang=view.findViewById(R.id.img_giohang_home);
       img_giohang.setOnClickListener(this);
        btn_fresh.setOnClickListener(this);
        btn_machiato.setOnClickListener(this);
        btn_milktea.setOnClickListener(this);
        btn_special.setOnClickListener(this);
        btn_che.setOnClickListener(this);
        onClick(view);
        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fresh: {
                Intent iten = new Intent(getActivity(), FreshFruitTea.class);
                startActivity(iten);
                break;
            }
            case R.id.milktea:{
                Intent iten = new Intent(getActivity(), MilkTea.class);
                startActivity(iten);
                break;
            }
            case R.id.macchiato:{
                Intent iten = new Intent(getActivity(), Macchiato.class);
                startActivity(iten);
                break;
            }
            case R.id.special:{
                Intent iten = new Intent(getActivity(), Special_Drink.class);
                startActivity(iten);
                break;
            }
            case R.id.che:{
                Intent iten = new Intent(getActivity(), Che.class);
                startActivity(iten);
                break;
            }
            case R.id.img_giohang_home:{
                Intent intent= new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}
