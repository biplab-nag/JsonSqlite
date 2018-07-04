package com.orionitbd.hello.jsonsqlite;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView idTV;
    public TextView titleTV;
    public TextView bodyTV;


    public PostViewHolder(View itemView) {
        super(itemView);
        idTV = itemView.findViewById(R.id.userId);
        titleTV = itemView.findViewById(R.id.posttitle);
        bodyTV = itemView.findViewById(R.id.body);

    }
}
