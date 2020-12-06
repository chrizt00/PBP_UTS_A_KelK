package com.hendrianto.uts_petshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.hendrianto.uts_petshop.R;
import com.hendrianto.uts_petshop.api.APICart;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.entity.Shop;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.ShopViewHolder> {
    private List<Shop> listData;
    private Context context;
    private List<String> title = new ArrayList<>();
    private String jenis,dataEmail;
    View v;

    public ShopRecyclerViewAdapter(List<Shop> listData,Context context,String dataEmail){
        this.listData = listData;
        this.context = context;
        this.dataEmail = dataEmail;
    }


    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        v = layoutInflater.inflate(R.layout.mainitem, parent, false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        final Shop shop = listData.get(position);
        holder.textTitle.setText(shop.getNama());
        holder.textPrice.setText("Rp." + String.valueOf(shop.getHarga()));
        Glide.with(context)
                .load(shop.getImgUrl())
                .apply(new RequestOptions().override(100, 100))
                .centerCrop()
                .into(holder.image);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jumlah = 1;
                tambahData(listData.get(position).getNama(),listData.get(position).getJenis(),
                        String.valueOf(listData.get(position).getHarga()),String.valueOf(jumlah),dataEmail);
                Toast.makeText(context,"Data added to Cart",Toast.LENGTH_SHORT).show();
                holder.buyStatus.setVisibility(View.VISIBLE);
                holder.buy.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textTitle,buyStatus;
        private TextView textPrice;
        private LinearLayout buy;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.itemImg);
            textTitle =itemView.findViewById(R.id.itemTitle);
            textPrice =itemView.findViewById(R.id.itemPrice);
            buyStatus =itemView.findViewById(R.id.buystatus);
            buy = itemView.findViewById(R.id.buy);
        }
    }

    public void tambahData(final String nama_barang, final String jenis, final String harga, final String jumlah,final String Email) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(POST, APICart.URL_ADD, new Response.Listener<String>() {
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
                Toast.makeText(context, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama_barang", nama_barang);
                params.put("jenis",jenis);
                params.put("harga", String.valueOf(harga));
                params.put("jumlah", String.valueOf(jumlah));
                params.put("email",Email);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
