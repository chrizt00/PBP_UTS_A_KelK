package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapFragment mapsFragment = new MapFragment();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        getSupportFragmentManager().beginTransaction().replace(R.id.mapfragment,mapsFragment).commit();
    }
}