package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hendrianto.uts_petshop.databinding.ActivityAboutusBinding;
import com.hendrianto.uts_petshop.databinding.ActivityNewsBinding;
import com.hendrianto.uts_petshop.entity.AboutUs;
import com.hendrianto.uts_petshop.entity.News;
import com.hendrianto.uts_petshop.list.ListAboutUs;

import java.util.ArrayList;
import java.util.Arrays;

public class NewsActivity extends AppCompatActivity {
    private News news = new News();
    ArrayList<News> listNews = new ArrayList<News>(Arrays.asList(news.getAllNews()));
    ActivityNewsBinding binding;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        int position = extras.getInt("key");
//        String test = String.valueOf(position);
//        Toast.makeText(this,listNews.get(position).getImageUrl(), Toast.LENGTH_SHORT).show();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news);
        img = findViewById(R.id.img1);
        binding.setNews(listNews.get(position));
        Glide.with(this)
                .load(listNews.get(position).getImageUrl())
                .apply(new RequestOptions().override(370,200))
                .into(img);
    }
}