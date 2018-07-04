package com.orionitbd.hello.jsonsqlite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orionitbd.hello.jsonsqlite.PostViewHolder;
import com.orionitbd.hello.jsonsqlite.R;
import com.orionitbd.hello.jsonsqlite.response.PostResponse;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context context;
    private List<PostResponse> postDataList;

    public PostAdapter( Context context, List<PostResponse>PostDataList ) {
        this.context =context;
        this.postDataList = PostDataList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.post_single_row,parent,false);
        return new PostViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
       holder.idTV.setText(String.valueOf(postDataList.get(position).getUserId()));
       holder.titleTV.setText(String.valueOf(postDataList.get(position).getTitle()));
       holder.bodyTV.setText(String.valueOf(postDataList.get(position).getBody()));
    }

    @Override
    public int getItemCount() {
        return postDataList.size();
    }
}
