package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.hendrianto.uts_petshop.databinding.ActivityAboutusBinding;
import com.hendrianto.uts_petshop.entity.AboutUs;
import com.hendrianto.uts_petshop.list.ListAboutUs;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
    ArrayList<AboutUs> listAbout;
    ActivityAboutusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        MapFragment mapsFragment = new MapFragment();
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_aboutus);
        listAbout = new ListAboutUs().AboutUs;
        binding.setAbout(listAbout.get(0));
        getSupportFragmentManager().beginTransaction().replace(R.id.mapfragment,mapsFragment).commit();
    }
}