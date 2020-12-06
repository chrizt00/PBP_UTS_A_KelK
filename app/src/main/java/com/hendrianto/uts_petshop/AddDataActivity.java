package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.api.APIUser;
import com.hendrianto.uts_petshop.entity.Shop;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;
import static com.android.volley.Request.Method.PUT;

public class AddDataActivity extends AppCompatActivity {
    TextView title;
    TextInputEditText nama,harga,url,id;
    AutoCompleteTextView jenis;
    String selectedJenis;
    Button cancel,add;
    private ProgressDialog progressDialog;
    String status;
    int ids;
    Shop shop;
    ImageView ivGambar;
    String[] AJenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Bundle extras = getIntent().getExtras();
        status = extras.getString("status");
        title = findViewById(R.id.title);
        progressDialog = new ProgressDialog(this);
        nama = findViewById(R.id.txtNama);
        harga = findViewById(R.id.txtHarga);
        url = findViewById(R.id.txtUrl);
        id = findViewById(R.id.txtId);
        ivGambar = findViewById(R.id.ivGambar);
        shop = new Shop();
        AJenis = getResources().getStringArray(R.array.Jenis);
        selectedJenis = AJenis[0];
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddDataActivity.this,
                R.array.Jenis, android.R.layout.simple_spinner_item);
        jenis = findViewById(R.id.txtJenis);
        jenis.setText(selectedJenis);
        jenis.setAdapter(adapter);

        jenis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        cancel = findViewById(R.id.btnBatal);
        add = findViewById(R.id.btnSimpan);

        if(status.equals("edit")) {
            ids = extras.getInt("shop");
            title.setText("Edit Shop Data");
            getDataById(ids);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().toString().length() < 1) {
                    nama.setError("Nama tidak boleh kosong");
                } else if (harga.getText().toString().length() < 1) {
                    harga.setError("Harga tidak boleh kosong");
                } else {
                    if (status.equals("edit")){
                        editData(Integer.valueOf(id.getText().toString()),nama.getText().toString(),jenis.getText().toString(),
                                Double.valueOf(harga.getText().toString()),url.getText().toString());
                    }else{
                        tambahData(nama.getText().toString(), jenis.getText().toString(), harga.getText().toString(), url.getText().toString());
                    }
                    Intent intent = new Intent(AddDataActivity.this, ShopDataActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    AddDataActivity.this.finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDataActivity.this, ShopDataActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                AddDataActivity.this.finish();
            }
        });

    }
    public void tambahData(final String nama, final String jenis, final String harga, final String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(POST, APIShop.URL_ADD, new Response.Listener<String>() {
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
                params.put("nama", nama);
                params.put("jenis",jenis);
                params.put("harga", String.valueOf(harga));
                params.put("imgUrl",url);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void editData(final int Eid,final String Enama, final String Ejenis, final double Eharga, final String Eurl) {
        RequestQueue queue = Volley.newRequestQueue(AddDataActivity.this);

        StringRequest stringRequest = new StringRequest(PUT, APIShop.URL_UPDATE + Eid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(AddDataActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
//                    Toast.makeText(getContext(),"catch", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                System.out.println(APIUser.URL_UPDATE+id);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                    /*
                        Disini adalah proses memasukan/mengirimkan parameter key dengan data value,
                        dan nama key nya harus sesuai dengan parameter key yang diminta oleh jaringan
                        API.
                    */
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", Enama);
                params.put("jenis", Ejenis);
                params.put("harga", String.valueOf(Eharga));
                params.put("imgUrl", Eurl);

                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void getDataById(int val) {
        RequestQueue queue = Volley.newRequestQueue(AddDataActivity.this);

        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APIShop.URL_SELECTBYID+val, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    int idss             = jsonObject.optInt("id");
                    String namas             = jsonObject.optString("nama");
                    String jeniss    = jsonObject.optString("jenis");
                    double hargas            = jsonObject.optDouble("harga");
                    String urls           = jsonObject.optString("imgUrl");

                    Glide.with(AddDataActivity.this)
                            .load(urls)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(ivGambar);
                    nama.setText(namas);
                    if(jeniss.equals("Mammals")){
                        selectedJenis = AJenis[0];
                    }else if(jeniss.equals("Fish")){
                        selectedJenis = AJenis[1];
                    }else if(jeniss.equals("Reptile")){
                        selectedJenis = AJenis[2];
                    }else if(jeniss.equals("Bird")){
                        selectedJenis = AJenis[3];
                    }else if(jeniss.equals("Accesories")){
                        selectedJenis = AJenis[4];
                    }else{
                        selectedJenis = AJenis[5];
                    }
                    harga.setText(String.valueOf(hargas));
                    url.setText(urls);
                    id.setText(Integer.toString(idss));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(AddDataActivity.this, response.optString("message"), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
}