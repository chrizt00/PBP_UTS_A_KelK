package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hendrianto.uts_petshop.adapter.ShopAdapter;
import com.hendrianto.uts_petshop.adapter.UserAdapter;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.api.APIUser;
import com.hendrianto.uts_petshop.entity.Shop;
import com.hendrianto.uts_petshop.entity.User;
import com.hendrianto.uts_petshop.entity.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class UserDataActivity extends AppCompatActivity {
    private List<Users> listData;
    private RecyclerView recycler;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        listData = new ArrayList<Users>();
        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APIUser.URL_SELECT
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    if(!listData.isEmpty())
                        listData.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject newObj = (JSONObject) jsonArray.get(i);
                        int id = newObj.optInt("id");
                        String nama = newObj.optString("nama");
                        String email = newObj.optString("email");
                        String telp = newObj.optString("telp");
                        String password = newObj.optString("password");
                        String verifCode = newObj.optString("verifCode");
                        int isVerifieds = newObj.optInt("isVerified");
                        //Membuat objek user
                        Users user = new Users(id,email,password,nama,telp,verifCode,isVerifieds);
                        //Menambahkan objek user tadi ke list user
                        listData.add(user);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                recycler = findViewById(R.id.recycler_view);
                adapter = new UserAdapter(UserDataActivity.this,listData);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserDataActivity.this);
                recycler.setLayoutManager(layoutManager);
                recycler.setAdapter(adapter);
                Toast.makeText(UserDataActivity.this, response.optString("message"),
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(UserDataActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}