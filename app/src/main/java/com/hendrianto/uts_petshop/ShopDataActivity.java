package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hendrianto.uts_petshop.adapter.ShopAdapter;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.api.APIUser;
import com.hendrianto.uts_petshop.api.ShopApiClient;
import com.hendrianto.uts_petshop.api.ShopApiInterface;
import com.hendrianto.uts_petshop.api.ShopResponse;
import com.hendrianto.uts_petshop.api.UserResponse;
import com.hendrianto.uts_petshop.api.UsersApiClient;
import com.hendrianto.uts_petshop.api.UsersApiInterface;
import com.hendrianto.uts_petshop.entity.Shop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.Response;

import static com.android.volley.Request.Method.GET;

public class ShopDataActivity extends AppCompatActivity {
    private FloatingActionButton addData,back;
    private List<Shop> listData;
    private RecyclerView recycler;
    private ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_data);
        addData = findViewById(R.id.addBtn);
        back = findViewById(R.id.backBtn);


        listData = new ArrayList<Shop>();
//        listData.add(new Shop(1,"abd","def",1231,"1231231"));
//        listData.add(new Shop(2,"abc","def",1231,"1231231"));

        getShop();
        recycler = findViewById(R.id.recycler_view);
        adapter = new ShopAdapter(ShopDataActivity.this,listData);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopDataActivity.this, AddDataActivity.class);
                intent.putExtra("status","tambah");
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopDataActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void getData() {
//        ShopApiInterface apiService = ShopApiClient.getClient().create(ShopApiInterface.class);
//        Call<ShopResponse> call = apiService.getAllShop("data");
//
//        call.enqueue(new Callback<ShopResponse>() {
//            @Override
//            public void onResponse(Call<ShopResponse> call, retrofit2.Response<ShopResponse> response) {
//                listData = response.body().getShop();
//            }
//
//            @Override
//            public void onFailure(Call<ShopResponse> call, Throwable t) {
//                Toast.makeText(ShopDataActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    public void getShop() {
        //Pendeklarasian queue
        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APIShop.URL_SELECT
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
//                    System.out.println("CUKIMAKKKKKK"+jsonArray.length());
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
                    adapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Toast.makeText(ShopDataActivity.this, response.optString("message"),
                        Toast.LENGTH_SHORT).show();
//                Toast.makeText(ShopDataActivity.this, listData.size(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(ShopDataActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}
