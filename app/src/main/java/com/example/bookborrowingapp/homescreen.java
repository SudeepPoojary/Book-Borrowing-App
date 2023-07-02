package com.example.bookborrowingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class homescreen extends AppCompatActivity {

    Button submit;
    EditText bookName, bookAuthor, bookImgUrl, renteeName,renteePhone ;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;

//    FirebaseDatabase rootNode;
//    DatabaseReference reference;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }
    ActionBar actionBar ;
    public boolean onSupportNavigateUp() {
        // Handle the back button press
        startActivity(new Intent(homescreen.this, com.example.bookborrowingapp.Navigate.class));
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        submit = findViewById(R.id.submitBtn);
        bookName = findViewById(R.id.bookName);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookImgUrl = findViewById(R.id.bookImageURL);
        renteeName = findViewById(R.id.renteeName);
        renteePhone = findViewById(R.id.renteePhone);
         actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(homescreen.this,MainActivity.class));
                }
            }
        };

    }
    private  void insertData(){
        Map<String, Object> map = new HashMap<>();
        map.put("bookName", bookName.getText().toString());
        map.put("bookAuthor",bookAuthor.getText().toString() );
        map.put("bookUrl",bookImgUrl.getText().toString());
        map.put("renteeName", renteeName.getText().toString());
        map.put("renteePhone", renteePhone.getText().toString());
        map.put("renteeUID", mAuth.getUid());

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference().child("Books");
//                reference.setValue(map);
//                reference.push();
        FirebaseDatabase.getInstance().getReference().child("Books").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(homescreen.this, "Update succesfull", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(homescreen.this, "Update Unsuccesfull", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}