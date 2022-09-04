package com.example.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookstore.Utils.utils;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.GioHang;
import com.example.bookstore.model.ThemGioHangActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookDetailsActivity extends AppCompatActivity {
    ImageView imgBook;
    TextView nameBook, authorBook, priceBook, detailBook;
    Button btAdd;
    Book book = new Book();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Intent intent = this.getIntent();
        btAdd = findViewById(R.id.button);
        imgBook = findViewById(R.id.book_images);
        nameBook = findViewById(R.id.book_names);
        authorBook = findViewById(R.id.book_author);
        priceBook = findViewById(R.id.book_price);
        detailBook = findViewById(R.id.book_detail);


        getBook(intent.getStringExtra("idBook"));
        initControl();
    }

    private void initControl() {
    btAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            themgiohang();
            startActivity(new Intent(BookDetailsActivity.this, ThemGioHangActivity.class));
            finish();
        }
    });
    }
    private void themgiohang()
    {

        GioHang gioHang = new GioHang();
        gioHang.setGiaSach(priceBook.getText().toString());
        gioHang.setSoLuong(1);
        gioHang.setTenSach(nameBook.getText().toString());
        gioHang.setHinhSach(book.getImage());
        utils.manggiohang.add(gioHang);
    }
    private void getBook(String id){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Books").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 book=snapshot.getValue(Book.class);
                Log.d("test",book.getImage());;
                nameBook.setText(book.getName());
                authorBook.setText(book.getAuthor());
                priceBook.setText(String.valueOf(book.getPrice()));
                Glide.with(BookDetailsActivity.this).load(book.getImage()).into(imgBook);
                detailBook.setText(book.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}