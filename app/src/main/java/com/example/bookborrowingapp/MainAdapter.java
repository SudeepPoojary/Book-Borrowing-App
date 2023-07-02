package com.example.bookborrowingapp;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    String phoneNumber = "";
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.bookName.setText(model.getBookName());
        holder.bookAutor.setText("Author: "+model.getBookAuthor());
        holder.renteeName.setText("Rentee Name:\n "+model.getRenteeName());
        holder.renteePhone.setText("Rentee Phone\n"+model.getRenteePhone());

        phoneNumber = model.getRenteePhone();
        Glide.with(holder.img.getContext())
                .load(model.getBookUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+model.getRenteePhone()));
                view.getContext().startActivity(i);
            }
        });

       }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent,false);
        return new myViewHolder(view);

    }


    static class myViewHolder extends RecyclerView.ViewHolder{
        Button call;
        ImageView img;
        TextView bookName, bookAutor, renteeName, renteePhone;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            bookName = itemView.findViewById(R.id.bookName);
            bookAutor = itemView.findViewById(R.id.bookAuthor);
            renteeName = itemView.findViewById(R.id.renteeName);
            renteePhone = itemView.findViewById(R.id.renteePhone);
            call = itemView.findViewById(R.id.delete);

        }


    }
}
