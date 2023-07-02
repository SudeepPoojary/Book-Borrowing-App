package com.example.bookborrowingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class Navigate extends AppCompatActivity {
    Button addBook,barrowBook,myBook,signOut;
    ImageView avatar;
    TextView info;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        addBook = findViewById(R.id.AddBook);
        barrowBook = findViewById(R.id.BorrowBook);
        myBook = findViewById(R.id.myBooks);
        signOut = findViewById(R.id.SignOut);
        avatar = findViewById(R.id.avatar);
        info = findViewById(R.id.info);
        mAuth= FirebaseAuth.getInstance();

        Glide.with(avatar)
                .load(mAuth.getCurrentUser().getPhotoUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .into(avatar);

        info.setText("Hello " + mAuth.getCurrentUser().getDisplayName());


        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigate.this, com.example.bookborrowingapp.homescreen.class));
                finish();
            }
        });

        barrowBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigate.this, com.example.bookborrowingapp.allBooks.class));
                finish();
            }
        });
        myBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigate.this, com.example.bookborrowingapp.myBooks01.class));
                finish();
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

    }
}