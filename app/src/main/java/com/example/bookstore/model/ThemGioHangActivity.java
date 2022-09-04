package com.example.bookstore.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookstore.R;
import com.example.bookstore.TinhTongTien;
import com.example.bookstore.Utils.utils;
import com.example.bookstore.adapter.GioHangAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ThemGioHangActivity extends AppCompatActivity {
    TextView tvgiohangtrong,tvtongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btMuahang;
    GioHangAdapter adapterGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_gio_hang);
        AnhXa();
        initControl();
        tinhtongtien();
    }

    private void tinhtongtien() {
        int tongtien= 0;
        for(int i = 0;i<utils.manggiohang.size();i++)
        {
            tongtien+=(Integer.parseInt(utils.manggiohang.get(i).getGiaSach()))*(utils.manggiohang.get(i).getSoLuong());
        }
        tvtongtien.setText(tongtien+"");
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterGioHang = new GioHangAdapter(getApplicationContext(), utils.manggiohang);
        recyclerView.setAdapter(adapterGioHang);
    }

    private void AnhXa() {
        tvgiohangtrong = findViewById(R.id.tvgiohangtrong);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleviewgiohang);
        tvtongtien = findViewById(R.id.tvTongTien);
        btMuahang = findViewById(R.id.btmuahang);
    }

    @Override
    protected void onStart() {

        super.onStart();
      EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void eventTinhTongTien(TinhTongTien event)
    {
        if(event!=null)
        {
            tinhtongtien();
        }
    }
}