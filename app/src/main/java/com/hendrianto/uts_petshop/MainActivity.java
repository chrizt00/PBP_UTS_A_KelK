package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.hendrianto.uts_petshop.adapter.ShopRecyclerViewAdapter;
import com.hendrianto.uts_petshop.entity.Shop;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Shop shop = new Shop();
    private List<String> checked = new ArrayList<>();
    private ShopRecyclerViewAdapter recyclerViewAdapter;
    private Shop[] shopss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extras = getIntent().getExtras();
        String jenis = extras.getString("keys");

        if(jenis.equals("Mammals")){
            shopss = shop.getDataMammals();
        }else if(jenis.equals("Fish")){
            shopss = shop.getDataFish();
        }else if(jenis.equals("Reptile")){
            shopss = shop.getDataReptile();
        }else if(jenis.equals("Bird")){
            shopss = shop.getDataBird();
        }else if(jenis.equals("Accesories")){
            shopss = shop.getDataAccesories();
        }else{
            shopss = shop.getDataFood();
        }
        final Shop[] shops = shopss;
        recyclerViewAdapter = new ShopRecyclerViewAdapter(shops,this,jenis);

        RecyclerView recyclerView = findViewById(R.id.recyclerdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        MaterialButton cartBtn = findViewById(R.id.cartbtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked = recyclerViewAdapter.getTitleChecked();
                if(checked.isEmpty()){
                    Toast.makeText(MainActivity.this, "Cart Empty", Toast.LENGTH_SHORT).show();
                }else{
                    showDialog(checked);
                }
            }
        });
    }

    public void showDialog(List<String> L){
        CharSequence[] items = checked.toArray(new CharSequence[checked.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cart Data");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}