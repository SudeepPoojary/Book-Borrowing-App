package com.example.bookborrowingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myBooks01 extends AppCompatActivity {
    RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    MainAdapter02 mainAdapter02;
    ActionBar actionBar ;
    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button press
        startActivity(new Intent(myBooks01.this, com.example.bookborrowingapp.Navigate.class));
        finish();
        return true;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //
        setContentView(R.layout.activity_my_books01);
        recyclerView = findViewById(R.id.rv1);
        mAuth = FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(
                                FirebaseDatabase.getInstance().getReference().child("Books")
                                        .orderByChild("renteeUID")
                                        .equalTo(mAuth.getUid())
                                , MainModel.class)
                            .build();
        mainAdapter02 = new MainAdapter02(options);
        recyclerView.setAdapter(mainAdapter02);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter02.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter02.stopListening();
    }
}