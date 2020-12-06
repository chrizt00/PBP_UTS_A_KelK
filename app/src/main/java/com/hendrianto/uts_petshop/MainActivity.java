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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.hendrianto.uts_petshop.adapter.ShopRecyclerViewAdapter;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.entity.Shop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class MainActivity extends AppCompatActivity {
    private List<Shop> listData,listDataFix;
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
        String dataEmail = extras.getString("email");
        listData = new ArrayList<Shop>();
        listDataFix = new ArrayList<Shop>();
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

        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APIShop.URL_SELECT
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    if(!listData.isEmpty())
                        listData.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        int id             = jsonObject.optInt("id");
                        String nama             = jsonObject.optString("nama");
                        String jenis    = jsonObject.optString("jenis");
                        double harga            = jsonObject.optDouble("harga");
                        String url           = jsonObject.optString("imgUrl");
                        //Membuat objek user
                        Shop shops = new Shop(id, nama, jenis, harga, url);
                        //Menambahkan objek user tadi ke list user
                        listData.add(shops);
                    }
                    for(int j=0;j<listData.size();j++){
                        if(jenis.equals(listData.get(j).getJenis())){
                            listDataFix.add(listData.get(j));
                        }
                    }
                    recyclerViewAdapter = new ShopRecyclerViewAdapter(listDataFix,MainActivity.this,dataEmail);

                    RecyclerView recyclerView = findViewById(R.id.recyclerdata);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(recyclerViewAdapter);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(MainActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

    }
}