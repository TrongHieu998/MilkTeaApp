package com.example.app_trasua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.app_trasua.fragment.FragmentDiemThuong;
import com.example.app_trasua.fragment.FragmentHome;
import com.example.app_trasua.fragment.FragmentNguoiDung;
import com.example.app_trasua.fragment.FragmentThongBao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNAV);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new FragmentHome());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId())
        {
            case R.id.item_Homne:
                if (position!=1) {
                    fragment = new FragmentHome();
                    position = 1;
                }
                break;
            case R.id.item_DiemThuong:
                if (position!=2) {
                    fragment = new FragmentDiemThuong();
                    position = 2;
                }
                break;
            case R.id.item_ThongBao:
                if (position!=3) {
                    fragment = new FragmentThongBao();
                    position = 3;
                }
                break;
            case R.id.item_TaiKhoan:
                if (position!=4) {
                    fragment = new FragmentNguoiDung();
                    position = 4;
                }
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment (Fragment fragment)
    {
        if (fragment !=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FrameLayoutMain,fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
