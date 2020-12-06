package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import com.hendrianto.uts_petshop.api.APITransaction;
import com.hendrianto.uts_petshop.api.ShopApiClient;
import com.hendrianto.uts_petshop.api.ShopApiInterface;
import com.hendrianto.uts_petshop.api.ShopResponse;
import com.hendrianto.uts_petshop.entity.Shop;
import com.hendrianto.uts_petshop.entity.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.Response;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;
import static com.android.volley.Request.Method.PUT;

public class TransactionActivity extends AppCompatActivity {
    TextView title;
    TextInputEditText jenis,harga,jumlah,email,id;
    Button btnSimpan,btnBack;
    AutoCompleteTextView namaBarang;
    private List<Shop> listData = new ArrayList<>();
    List<String> ANamaBarang = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int getIndex,ids;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        title = findViewById(R.id.title);
        id = findViewById(R.id.txtId);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnBack = findViewById(R.id.btnBack);
        jenis = findViewById(R.id.txtJenis);
        harga = findViewById(R.id.txtHarga);
        jumlah = findViewById(R.id.txtJumlah);
        email = findViewById(R.id.txtEmail);
        namaBarang = findViewById(R.id.txtNamaBarang);
        Bundle extras = getIntent().getExtras();
        jumlah.setText("0", TextView.BufferType.EDITABLE);
        status = extras.getString("status");
        RequestQueue queue = Volley.newRequestQueue(TransactionActivity.this);
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

                }catch (JSONException e){
                    e.printStackTrace();
                }

                for(int i=0;i<listData.size();i++){
                    ANamaBarang.add(listData.get(i).getNama());
                }
            namaBarang.setText("Choose Item");
            adapter = new ArrayAdapter<String>(TransactionActivity.this,android.R.layout.simple_dropdown_item_1line,ANamaBarang);
            namaBarang.setAdapter(adapter);

            namaBarang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    namaBarang.getText();
                    for(int j=0;j<listData.size();j++){
                        if(listData.get(j).getNama().equals(namaBarang.getText().toString())){
                            getIndex = j;
                        }
                    }
                    jenis.setText(listData.get(getIndex).getJenis());
                    harga.setText(String.valueOf(listData.get(getIndex).getHarga()));
                    jenis.setEnabled(false);
                    harga.setEnabled(false);
                }
            });
                if(!status.equals("tambah")){
                    title.setText("Edit Transaction Data");
                    btnBack.setText("Back To Data");
                    ids = extras.getInt("sendid");
                    getDataById(ids);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(TransactionActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

//        Toast.makeText(TransactionActivity.this, "abc"+listData.size(), Toast.LENGTH_SHORT).show();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(status.equals("tambah")){
                    intent = new Intent(TransactionActivity.this, AdminActivity.class);
                }else{
                    intent = new Intent(TransactionActivity.this, TransactionDataActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                TransactionActivity.this.finish();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(namaBarang.getText().toString().equals("Choose Item")){
                    namaBarang.setError("Pilih Data");
                }else if(Integer.valueOf(jumlah.getText().toString())<1){
                    jumlah.setError("Jumlah Tidak Valid");
                }else if(email.getText().toString().length()<1){
                    email.setError("Email tidak boleh kosong");
                }else {
                    if (status.equals("tambah")) {
                        tambahData(namaBarang.getText().toString(), jenis.getText().toString(),
                                harga.getText().toString(), jumlah.getText().toString(), email.getText().toString());
                        Toast.makeText(TransactionActivity.this,"Transaction Success",Toast.LENGTH_SHORT).show();
                        clear();
                    } else {
                        editData(Integer.valueOf(id.getText().toString()),namaBarang.getText().toString(),
                                jenis.getText().toString(),Double.valueOf(harga.getText().toString()),email.getText().toString(),
                                Integer.valueOf(jumlah.getText().toString()));
                        Intent intent = new Intent(TransactionActivity.this, TransactionDataActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        TransactionActivity.this.finish();
                    }
                }
            }
        });
    }


    public void getData() {
        ShopApiInterface apiService = ShopApiClient.getClient().create(ShopApiInterface.class);
        Call<ShopResponse> call = apiService.getAllShop("data");

        call.enqueue(new Callback<ShopResponse>() {
            @Override
            public void onResponse(Call<ShopResponse> call, retrofit2.Response<ShopResponse> response) {
                listData = response.body().getShop();
//                System.out.println(listData.get(0));
            }

            @Override
            public void onFailure(Call<ShopResponse> call, Throwable t) {
                Toast.makeText(TransactionActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void tambahData(final String nama, final String jenis, final String harga, final String jumlah, final String email) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(POST, APITransaction.URL_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                params.put("harga", harga);
                params.put("jumlah",jumlah);
                params.put("email",email);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void clear(){
        namaBarang.setText("Choose Item");
        jenis.setText("");
        harga.setText("");
        jumlah.setText("0", TextView.BufferType.EDITABLE);
        email.setText("Admin");
    }



    public void getDataById(int val) {
        RequestQueue queue = Volley.newRequestQueue(TransactionActivity.this);

        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APITransaction.URL_SELECTBYID+val, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    int idss             = jsonObject.optInt("id");
                    String namas             = jsonObject.optString("nama_barang");
                    String jeniss    = jsonObject.optString("jenis");
                    int jumlahs         = jsonObject.optInt("jumlah");
                    double hargas            = jsonObject.optDouble("harga");
                    String emails         = jsonObject.optString("email");

                    id.setText(String.valueOf(idss));
                    namaBarang.setText(namas);
                    jenis.setText(jeniss);
                    jumlah.setText(String.valueOf(jumlahs));
                    harga.setText(String.valueOf(hargas));
                    email.setText(emails);
                    jenis.setEnabled(false);
                    harga.setEnabled(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }


    public void editData(final int Eid,final String Enama, final String Ejenis, final double Eharga, final String Eemail, final int Ejumlah) {
        RequestQueue queue = Volley.newRequestQueue(TransactionActivity.this);

        StringRequest stringRequest = new StringRequest(PUT, APITransaction.URL_UPDATE + Eid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(TransactionActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                params.put("nama_barang", Enama);
                params.put("jenis",Ejenis);
                params.put("harga", String.valueOf(Eharga));
                params.put("jumlah",String.valueOf(Ejumlah));
                params.put("email",Eemail);

                return params;
            }
        };
        queue.add(stringRequest);
    }

}