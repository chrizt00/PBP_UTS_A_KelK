package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hendrianto.uts_petshop.adapter.CartAdapter;
import com.hendrianto.uts_petshop.adapter.ShopAdapter;
import com.hendrianto.uts_petshop.api.APICart;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.api.APITransaction;
import com.hendrianto.uts_petshop.entity.Cart;
import com.hendrianto.uts_petshop.entity.Shop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

public class CartActivity extends AppCompatActivity {
    private ExtendedFloatingActionButton checkout;
    private List<Cart> listData;
    private RecyclerView recycler;
    private CartAdapter adapter;
    TextView emails,hargas;
    String tempEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listData = new ArrayList<Cart>();
        emails = findViewById(R.id.email);
        hargas = findViewById(R.id.harga);
        checkout = findViewById(R.id.checkout);
        recycler = findViewById(R.id.recycler_view);
        Bundle extras = getIntent().getExtras();
        tempEmail = extras.getString("key");
        emails.setText(extras.getString("key"));


        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APICart.URL_SELECT
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                double hargaT=0;
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    if(!listData.isEmpty())
                        listData.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        String nama_barang             = jsonObject.optString("nama_barang");
                        String jenis    = jsonObject.optString("jenis");
                        double harga            = jsonObject.optDouble("harga");
                        int jumlah            = jsonObject.optInt("jumlah");
                        String email           = jsonObject.optString("email");
                        //Membuat objek user
                        Cart cart = new Cart(nama_barang, jenis, email, harga,jumlah);
                        //Menambahkan objek user tadi ke list user
                        listData.add(cart);
                        hargaT+=(jumlah*harga);
                    }
                    String desired = String.format("%.2f",Double.parseDouble(String.valueOf(hargaT)));
                    hargas.setText("Rp. "+desired);
                    adapter = new CartAdapter(CartActivity.this,listData);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
                    recycler.setLayoutManager(layoutManager);
                    recycler.setAdapter(adapter);

                    checkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!listData.isEmpty()) {
                                for (int j = 0; j < listData.size(); j++) {
                                    tambahData(
                                            listData.get(j).getNama_barang(),
                                            listData.get(j).getJenis(),
                                            String.valueOf(listData.get(j).getHarga()),
                                            String.valueOf(listData.get(j).getJumlah()),
                                            listData.get(j).getEmail()
                                    );
                                }
                                deleteData();
                            }
                            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                            intent.putExtra("email",tempEmail);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }
                    });

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(CartActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }

    public void tambahData(final String nama, final String jenis, final String harga, final String jumlah, final String email) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(POST, APITransaction.URL_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama_barang", nama);
                params.put("jenis",jenis);
                params.put("harga", String.valueOf(harga));
                params.put("jumlah",String.valueOf(jumlah));
                params.put("email",email);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void deleteData(){
        RequestQueue queue = Volley.newRequestQueue(CartActivity.this);

        StringRequest stringRequest = new StringRequest(DELETE, APICart.URL_DELETE , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(CartActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
}