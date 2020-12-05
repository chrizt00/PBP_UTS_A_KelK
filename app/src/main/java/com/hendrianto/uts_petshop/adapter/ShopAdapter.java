package com.hendrianto.uts_petshop.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hendrianto.uts_petshop.AddDataActivity;
import com.hendrianto.uts_petshop.R;
import com.hendrianto.uts_petshop.ShopDataActivity;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.entity.Shop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.adapterUserViewHolder> {
    private List<Shop> shopList;
//    private List<Shop> shopListFiltered;
    private Context context;
    private View view;
    private ShopAdapter adapter;

    public ShopAdapter(Context context, List<Shop> shopList){
        this.context = context;
        this.shopList = shopList;
        this.adapter = this;
//        System.out.println("masuk sini?"+shopList.size());
//        this.shopListFiltered = shopList;
    }

    @NonNull
    @Override
    public adapterUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.activity_adapter_shop, parent, false);
        return new adapterUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.adapterUserViewHolder holder, int position) {
        final Shop shop = shopList.get(position);

        holder.txtNama.setText(shop.getNama());
        holder.txtJenis.setText(shop.getJenis());
        holder.txtHarga.setText(String.valueOf(shop.getHarga()));
        Glide.with(context)
                .load(shop.getImgUrl())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivGambar);

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddDataActivity.class);
                intent.putExtra("status","edit");
                intent.putExtra("shop", shop.getId());
//                Toast.makeText(context,"abc:"+shop.getId(),Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

        holder.ivHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title dialog
                alertDialogBuilder.setTitle("Delete Action");

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage("Click Yes to Confirm")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteData(shop.getId());
                                Intent intent = new Intent(context, ShopDataActivity.class);
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // jika tombol ini diklik, akan menutup dialog
                                // dan tidak terjadi apa2
                                dialog.cancel();
                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();
            }
        });
    }
    public class adapterUserViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtJenis, ivEdit, ivHapus;
        private ImageView ivGambar;

        public adapterUserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama         = (TextView) itemView.findViewById(R.id.txtNama);
            txtHarga        = (TextView) itemView.findViewById(R.id.txtHarga);
            txtJenis         =(TextView) itemView.findViewById(R.id.txtJenis);
            ivGambar        = (ImageView) itemView.findViewById(R.id.ivGambar);
            ivEdit          = (TextView) itemView.findViewById(R.id.ivEdit);
            ivHapus         = (TextView) itemView.findViewById(R.id.ivHapus);
        }
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }
    public void deleteData(int npm){
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(DELETE, APIShop.URL_DELETE+npm , new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}
