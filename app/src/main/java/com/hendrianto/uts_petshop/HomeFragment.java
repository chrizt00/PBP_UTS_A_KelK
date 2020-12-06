package com.hendrianto.uts_petshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        CardView to = v.findViewById(R.id.tobuy);
        CardView to2 = v.findViewById(R.id.tobuy2);
        CardView to3 = v.findViewById(R.id.tobuy3);
        CardView to4 = v.findViewById(R.id.tobuy4);
        CardView to5 = v.findViewById(R.id.tobuy5);
        CardView to6 = v.findViewById(R.id.tobuy6);
        String dataEmail = getArguments().getString("email");
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplication(),MainActivity.class);
                i.putExtra("keys","Mammals");
                i.putExtra("email",dataEmail);
                startActivity(i);
            }
        });
        to2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplication(),MainActivity.class);
                i.putExtra("keys","Fish");
                i.putExtra("email",dataEmail);
                startActivity(i);
            }
        });
        to3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplication(),MainActivity.class);
                i.putExtra("keys","Reptile");
                i.putExtra("email",dataEmail);
                startActivity(i);
            }
        });
        to4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplication(),MainActivity.class);
                i.putExtra("keys","Bird");
                i.putExtra("email",dataEmail);
                startActivity(i);
            }
        });
        to5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplication(),MainActivity.class);
                i.putExtra("keys","Accesories");
                i.putExtra("email",dataEmail);
                startActivity(i);
            }
        });
        to6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplication(),MainActivity.class);
                i.putExtra("keys","Food");
                i.putExtra("email",dataEmail);
                startActivity(i);
            }
        });
        return v;
    }
}