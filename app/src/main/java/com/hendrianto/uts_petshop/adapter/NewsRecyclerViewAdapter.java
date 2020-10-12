package com.hendrianto.uts_petshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.hendrianto.uts_petshop.NewsActivity;
import com.hendrianto.uts_petshop.R;
import com.hendrianto.uts_petshop.entity.News;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    ArrayList<News> dataholder;
    Activity activity;
    Context context;

    public NewsRecyclerViewAdapter(Context context,ArrayList<News> dataholder, Activity activity){
        this.dataholder = dataholder;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem,parent,false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.titleNews.setText(dataholder.get(position).getTitle());
        holder.captionNews.setText(dataholder.get(position).getCaption());
            Glide.with(activity)
                    .load(dataholder.get(position).getImageUrl())
                    .apply(new RequestOptions().override(130,130))
                    .centerCrop()
                    .into(holder.imageNews);


        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity, "Klik"+position, Toast.LENGTH_SHORT).show();
                int value = position;
                Intent i = new Intent(context, NewsActivity.class);
                i.putExtra("key",value);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageNews;
        private TextView titleNews;
        private TextView captionNews;
        private MaterialCardView itemCard;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.itemImg);
            titleNews = itemView.findViewById(R.id.newsTitle);
            captionNews = itemView.findViewById(R.id.newsCaption);
            itemCard = itemView.findViewById(R.id.item_card);
        }
    }
}
