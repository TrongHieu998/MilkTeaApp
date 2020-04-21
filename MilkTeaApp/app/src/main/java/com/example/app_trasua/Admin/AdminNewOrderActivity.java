package com.example.app_trasua.Admin;

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

import com.example.app_trasua.Model.AdminOrder;
import com.example.app_trasua.Prevalent.Prevalent;
import com.example.app_trasua.R;
import com.example.app_trasua.class_diemthuong.DonHangcCuaToiActivity;
import com.example.app_trasua.class_home.MilkTea;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AdminNewOrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_order);
        recyclerView=findViewById(R.id.recycleView_newOrder);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("HoaDon")
                .child(Prevalent.currentOnlineUser.getSDT());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(AdminNewOrderActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<AdminOrder> options =
                new FirebaseRecyclerOptions.Builder<AdminOrder>()
                        .setQuery(databaseReference, AdminOrder.class)
                        .build();
        FirebaseRecyclerAdapter<AdminOrder, AdminOrderViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminOrder, AdminOrderViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final AdminOrderViewHolder holder, final int position, @NonNull final AdminOrder model) {
                        holder.txt_ten.setText(model.getItemHoTen());
                        holder.txt_diachi.setText(model.getItemDiachi());
                        holder.txt_sdt.setText(model.getItemSDT());
                        holder.txt_email.setText(model.getItemEmail());
                        holder.txt_tinhtrang.setText(model.getItemTinhtrang());
                        holder.txt_tongtien.setText(model.getItemTongtien());

                        holder.btn_showOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Chỗ này test
//                                databaseReference.child("SanPham")
//                                        .child(model.getItemId())
//                                        .updateChildren()

                                //Chỗ này test
                                //String ni=getRef(position).child(Prevalent.currentOnlineUser.getSDT()).getKey();
                                String ID=FirebaseDatabase.getInstance().getReference().child("HoaDon")
                                        .child(Prevalent.currentOnlineUser.getSDT()).getKey();

                                DatabaseReference chiTietRef = FirebaseDatabase.getInstance().getReference("HoaDon");
                                chiTietRef.child(ID).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        // hiện chi tiết
                                        ArrayList<String> chiTietKeys;
                                        chiTietKeys = new ArrayList<>();
                                        for(DataSnapshot chiTiet : dataSnapshot.getChildren())
                                        {
                                            String key = chiTiet.getKey();
                                            chiTietKeys.add(key);
                                        }
                                        Intent intent= new Intent(AdminNewOrderActivity.this, DonHangcCuaToiActivity.class);
                                        intent.putExtra("id",chiTietKeys.get(position));
                                        Toast.makeText(AdminNewOrderActivity.this, ""+chiTietKeys.get(position), Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                                //Toast.makeText(AdminNewOrderActivity.this, chiTietKeys.size() + "", Toast.LENGTH_SHORT).show();
                                /*Intent intent= new Intent(AdminNewOrderActivity.this, DonHangcCuaToiActivity.class);
                                intent.putExtra("id",chiTietKeys.get(position));
                                Toast.makeText(AdminNewOrderActivity.this, ""+chiTietKeys.get(position), Toast.LENGTH_SHORT).show();
                                startActivity(intent);*/
                            }
                        });
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CharSequence options[]= new CharSequence[]{
                                        "Yes",
                                        "No"
                                };
                                AlertDialog.Builder builder=new AlertDialog.Builder(AdminNewOrderActivity.this);
                                builder.setTitle("Xóa sản phẩm trong đơn đặt hàng?");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        if(i==0)
                                        {
                                            String uID=getRef(position).getKey();
                                            RemoveOrder(uID);
                                        }
                                        else
                                        {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                    }
                    @NonNull
                    @Override
                    public AdminOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_new_order,parent,false);
                        return new AdminOrderViewHolder(view);
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    public static class AdminOrderViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_ten,txt_sdt,txt_diachi,txt_email,txt_tinhtrang,txt_tongtien;
        public Button btn_showOrder;
        public AdminOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ten=itemView.findViewById(R.id.txt_ten_order);
            txt_sdt=itemView.findViewById(R.id.txt_sdt_order);
            txt_diachi=itemView.findViewById(R.id.txt_diachi_order);
            txt_email=itemView.findViewById(R.id.txt_email_order);
            txt_tinhtrang=itemView.findViewById(R.id.txt_tinhtrang_order);
            txt_tongtien=itemView.findViewById(R.id.txt_tongtien_order);
            btn_showOrder=itemView.findViewById(R.id.btn_showOrder);

        }
    }
    private void RemoveOrder(String uID) {
        databaseReference.child(uID).removeValue();
    }

}
