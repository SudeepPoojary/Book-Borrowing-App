package com.example.bookborrowingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainAdapter02 extends FirebaseRecyclerAdapter<MainModel, MainAdapter02.myViewHolder01> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter02(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder01 holder, int position, @NonNull MainModel model) {
        holder.bookName.setText(model.getBookName());
        holder.bookAutor.setText("Author: "+model.getBookAuthor());
        holder.renteeName.setText("Rentee Name:\n "+model.getRenteeName());
        holder.renteePhone.setText("Rentee Phone\n"+model.getRenteePhone());

        Glide.with(holder.img.getContext())
                .load(model.getBookUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Books")
                        .orderByChild("bookName")
                        .equalTo(model.getBookName())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    // Delete the book
                                    snapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder01 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item02, parent,false);
        return new myViewHolder01(view);
    }

    class myViewHolder01 extends RecyclerView.ViewHolder{
        Button delete;
        ImageView img;
        TextView bookName, bookAutor, renteeName, renteePhone;

        public myViewHolder01(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            bookName = itemView.findViewById(R.id.bookName);
            bookAutor = itemView.findViewById(R.id.bookAuthor);
            renteeName = itemView.findViewById(R.id.renteeName);
            renteePhone = itemView.findViewById(R.id.renteePhone);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}