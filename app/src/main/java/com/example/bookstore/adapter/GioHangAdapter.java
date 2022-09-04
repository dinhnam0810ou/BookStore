package com.example.bookstore.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookstore.ImageClick;
import com.example.bookstore.R;
import com.example.bookstore.TinhTongTien;
import com.example.bookstore.Utils.utils;
import com.example.bookstore.model.GioHang;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {

    Context context;
    List<GioHang> gioHangList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gio_hang,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.item_tensp_giohang.setText(gioHang.getTenSach());
        holder.item_soluong_giohang.setText(1+"");
        Glide.with(context).load(gioHang.getHinhSach()).into(holder.item_image_giohang);
        holder.item_gia_giohang.setText(gioHang.getGiaSach());

        holder.item_giadonhang_giohang.setText(gioHang.getGiaSach());
        holder.setImageClick(new ImageClick() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if(giatri==1)
                {
                    if(gioHangList.get(pos).getSoLuong()>1)
                    {
                        int soluongmoi = gioHangList.get(pos).getSoLuong()-1;
                        gioHangList.get(pos).setSoLuong(soluongmoi);
                        holder.item_soluong_giohang.setText(gioHangList.get(pos).getSoLuong()+"");
                        int giasach = Integer.parseInt(gioHangList.get(pos).getGiaSach());
                        int soluong = Integer.parseInt(String.valueOf(gioHangList.get(pos).getSoLuong()));
                        int gia = soluong*giasach;
                        holder.item_giadonhang_giohang.setText(gia+"");
                        EventBus.getDefault().postSticky(new TinhTongTien());
                    }
                    else if(gioHangList.get(pos).getSoLuong()==1)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thong bao");
                        builder.setMessage("Ban co muon xoa san pham nay ra khoi gio hang");
                        builder.setPositiveButton("Dong y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                utils.manggiohang.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongTien());
                            }
                        });
                        builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                }
                else
                {
                    if(gioHangList.get(pos).getSoLuong()<11)
                    {
                        int soluongmoi = gioHangList.get(pos).getSoLuong()+1;
                        gioHangList.get(pos).setSoLuong(soluongmoi);
                    }
                    holder.item_soluong_giohang.setText(gioHangList.get(pos).getSoLuong()+"");
                    int giasach = Integer.parseInt(gioHangList.get(pos).getGiaSach());
                    int soluong = Integer.parseInt(String.valueOf(gioHangList.get(pos).getSoLuong()));
                    int gia = soluong*giasach;
                    holder.item_giadonhang_giohang.setText(gia+"");
                    EventBus.getDefault().postSticky(new TinhTongTien());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_image_giohang,imgCong,imgTru;
        TextView item_tensp_giohang,item_gia_giohang,item_giadonhang_giohang,item_soluong_giohang;
        ImageClick imageClick;
        public MyViewHolder(@NonNull View itemview)
        {
            super(itemview);
            item_image_giohang = itemview.findViewById(R.id.item_image_giohang);
            item_tensp_giohang = itemview.findViewById(R.id.item_tensp_giohang);
            item_giadonhang_giohang = itemview.findViewById(R.id.item_giadonhang_giohang);
            item_soluong_giohang = itemview.findViewById(R.id.item_soluong_giohang);
            item_gia_giohang = itemview.findViewById(R.id.item_gia_giohang);
            imgCong = itemview.findViewById(R.id.item_cong_giohang);
            imgTru = itemview.findViewById(R.id.item_tru_giohang);
            imgCong.setOnClickListener(this);
            imgTru.setOnClickListener(this);
        }

        public void setImageClick(ImageClick imageClick) {
            this.imageClick = imageClick;
        }

        @Override
        public void onClick(View view) {
            if(view==imgTru)
            {
                imageClick.onImageClick(view,getAdapterPosition(),1);
            }
            else
            {
                imageClick.onImageClick(view,getAdapterPosition(),2);
            }
        }
    }

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

}
