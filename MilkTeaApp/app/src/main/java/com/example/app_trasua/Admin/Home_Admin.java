package com.example.app_trasua.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_trasua.R;

public class Home_Admin extends AppCompatActivity {
    Button btn_fresh,btn_machiato,btn_milktea,btn_special,btn_che,btn_checkOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        btn_fresh=(Button) findViewById(R.id.fresh_admin);
        btn_machiato=(Button) findViewById(R.id.macchiato_admin) ;
        btn_milktea=(Button) findViewById(R.id.milktea_admin);
        btn_special=(Button) findViewById(R.id.special_admin);
        btn_che=(Button) findViewById(R.id.che_admin);
        btn_checkOrder=findViewById(R.id.btn_checkOrder);
        btn_fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iten = new Intent(Home_Admin.this, MilkTea_Admin.class);
                startActivity(iten);
            }
        });
        btn_milktea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iten = new Intent(Home_Admin.this, MilkTea_Admin.class);
                startActivity(iten);
            }
        });
        btn_machiato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iten = new Intent(Home_Admin.this, MilkTea_Admin.class);
                startActivity(iten);
            }
        });
        btn_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iten = new Intent(Home_Admin.this, MilkTea_Admin.class);
                startActivity(iten);
            }
        });
        btn_che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iten = new Intent(Home_Admin.this, MilkTea_Admin.class);
                startActivity(iten);
            }
        });
        btn_checkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Home_Admin.this,AdminNewOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
