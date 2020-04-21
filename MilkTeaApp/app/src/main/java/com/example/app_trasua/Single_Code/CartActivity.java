package com.example.app_trasua.Single_Code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_trasua.Model.Cart;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.example.app_trasua.class_home.MilkTea;
import com.example.app_trasua.class_home.Special_Drink;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btn_thanhtoan,btn_tieptucmuahang;
    private TextView txt_tongtien,txt_msg;
    private Double tongtien=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView=findViewById(R.id.recycleView_card);
        btn_tieptucmuahang=findViewById(R.id.btn_tieptucmuahang_cart);
        btn_thanhtoan=findViewById(R.id.btn_thanhtoan_cart);
        btn_tieptucmuahang.findViewById(R.id.btn_tieptucmuahang_cart);
        txt_tongtien=findViewById(R.id.txt_tongtien_cart);
        txt_msg=findViewById(R.id.txt_msgl);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(CartActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_tongtien.setText("Tong Tien="+ String.valueOf(tongtien));
                Intent intent= new Intent(CartActivity.this,ConfirmFinal.class);
                intent.putExtra("Tong Tien",String.valueOf(tongtien));
                startActivity(intent);
                finish();
            }
        });
        btn_tieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CartActivity.this, MilkTea.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        //CheckOrderState();

        super.onStart();
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("GioHang");
        final DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("HoaDon");
        FirebaseRecyclerOptions<Cart> options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(databaseReference.child("User View")
                .child(Prevalent.currentOnlineUser.getSDT()).child("SanPham"),Cart.class)
                        .build();
        FirebaseRecyclerAdapter<Cart,CartViewHolder> adapter=
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model) {
                        holder.txt_ten.setText(model.getItemTen());
                        holder.txt_gia.setText(model.getItemGia());
                        holder.txt_soluong.setText(model.getItemSoluong());
  //                      holder.txt_tongtien.setText(model.getItemTongtien());
                        double tt=((Double.valueOf(model.getItemGia())))*(Integer.valueOf(model.getItemSoluong()));
                        tongtien=tongtien+tt;

                        holder.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CharSequence option[]= new CharSequence[]
                                        {
                                                "Edit",
                                                "Remove"
                                        };
                                AlertDialog.Builder builder= new AlertDialog.Builder(CartActivity.this);
                                builder.setTitle("Tùy chọn:");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int i)
                                    {
                                        if(i==0)
                                        {
                                            Intent intent= new Intent(CartActivity.this,ProductDetailActivity.class);
                                            intent.putExtra("itemId",model.getItemId());
                                            startActivity(intent);
                                        }
                                        if(i>=1)
                                        {
                                            db.child(Prevalent.currentOnlineUser.getSDT()).child("CT_DonDatHang").child("SanPham")
                                                    .child(model.getItemId())
                                                    .removeValue()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful())
                                                            {

                                                            }

                                                        }
                                                    });
                                            databaseReference.child("User View")
                                                    .child(Prevalent.currentOnlineUser.getSDT())
                                                    .child("SanPham")
                                                    .child(model.getItemId())
                                                    .removeValue()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful())
                                                            {
                                                                Toast.makeText(CartActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                                                Intent intent= new Intent(CartActivity.this,MilkTea.class);
                                                                startActivity(intent);
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
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
//    private void CheckOrderState()
//    {
//        DatabaseReference databaseReference;
//        databaseReference=FirebaseDatabase.getInstance().getReference().child("DonDatHang").child(Prevalent.currentOnlineUser.getSDT());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
//                    String itemTinhtrang=dataSnapshot.child("itemTinhtrang").getValue().toString();
//                    String itemName= dataSnapshot.child("itemName").getValue().toString();
//                    if(itemTinhtrang.equals("Đã giao hàng"))
//                    {
//                        txt_tongtien.setText(itemName+ "\n đơn hàng được vận chuyển thành công");
//                        recyclerView.setVisibility(View.GONE);
//                        txt_msg.setVisibility(View.GONE);
//                        txt_msg.setText("Xin chúc mừng, đơn hàng của bạn đã được đặt thành công. Bạn sẽ sớm nhận được đơn hàng");
//                        btn_thanhtoan.setVisibility(View.GONE);
//                        Toast.makeText(CartActivity.this, "Bạn có thể mua nhiều sản phẩm hơn", Toast.LENGTH_SHORT).show();
//                    }
//                    else if(itemTinhtrang.equals("Chưa giao hàng"))
//                    {
//                        txt_tongtien.setText("Tình Trạng=Chưa giao hàng");
//                        recyclerView.setVisibility(View.GONE);
//                        txt_msg.setVisibility(View.GONE);
//                        btn_thanhtoan.setVisibility(View.GONE);
//                        Toast.makeText(CartActivity.this, "Bạn có thể mua nhiều sản phẩm hơn", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
