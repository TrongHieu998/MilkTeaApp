package com.example.app_trasua.class_diemthuong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_trasua.Admin.AdminNewOrderActivity;
import com.example.app_trasua.Model.AdminOrder;
import com.example.app_trasua.Model.Apdapter_Cart;
import com.example.app_trasua.Model.Cart;
import com.example.app_trasua.Model.CartDetail;
import com.example.app_trasua.Model.Confirm;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.example.app_trasua.Single_Code.CartViewHolder;
import com.example.app_trasua.Single_Code.ConfirmFinal;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangcCuaToiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    private TextView txt_tongtien;
    private Double tongtien=0.0;
    private String UserId="";
    String key="";
    Adapter_donhang myAdapter;

    // phúc làm
    ArrayList<CartDetail> cartDetailArrayList;
    ArrayList<String> cartDetailKeys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hangc_cua_toi);
        UserId = getIntent().getStringExtra("id");
        recyclerView = findViewById(R.id.recycleView_dhct);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(DonHangcCuaToiActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("HoaDon")
                .child(Prevalent.currentOnlineUser.getSDT()).child(UserId);
//        databaseReference=FirebaseDatabase.getInstance().getReference().child("GioHang")
//                .child("Admin View").child(Prevalent..getSDT()).child("SanPham");
        databaseReference.child("arrayList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartDetailArrayList = new ArrayList<>();
                cartDetailKeys = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= dataSnapshot.getKey();
                    CartDetail cartDetail = snapshot.getValue(CartDetail.class);
                    cartDetailArrayList.add(cartDetail);
                    String cartKey = cartDetail.getItemId();
                    cartDetailKeys.add(cartKey);
                }
                //myAdapter.notifyDataSetChanged();
                myAdapter = new Adapter_donhang(DonHangcCuaToiActivity.this, cartDetailKeys, cartDetailArrayList);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    protected void onStart()
    {
        super.onStart();
        FirebaseRecyclerOptions<Cart> options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(databaseReference,Cart.class)
                        .build();
        FirebaseRecyclerAdapter<Cart,CartViewHolder> adapter=
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int i, @NonNull Cart model) {
                        holder.txt_ten.setText(model.getItemTen());
                        holder.txt_gia.setText(model.getItemGia());
                        holder.txt_soluong.setText(model.getItemSoluong());
//                        double tt=((Double.valueOf(model.getItemGia())))*(Integer.valueOf(model.getItemSoluong()));
//                        tongtien=tongtien+tt;
                    }
                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_gio_hang,parent,false);
                        CartViewHolder holder= new CartViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


}
