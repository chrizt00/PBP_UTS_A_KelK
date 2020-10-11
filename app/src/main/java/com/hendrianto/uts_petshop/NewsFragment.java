package com.hendrianto.uts_petshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendrianto.uts_petshop.entity.News;

import java.util.ArrayList;
import java.util.Arrays;

public class NewsFragment extends Fragment {
    private News news = new News();
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<News> dataholder = new ArrayList<News>(Arrays.asList(news.getAllNews()));
    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NewsRecyclerViewAdapter(getContext(),dataholder,getActivity()));
        return v;
    }
}