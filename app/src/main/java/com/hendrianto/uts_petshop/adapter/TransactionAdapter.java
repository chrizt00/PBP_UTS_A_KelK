package com.hendrianto.uts_petshop.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.hendrianto.uts_petshop.AddDataActivity;
import com.hendrianto.uts_petshop.R;
import com.hendrianto.uts_petshop.ShopDataActivity;
import com.hendrianto.uts_petshop.TransactionActivity;
import com.hendrianto.uts_petshop.TransactionDataActivity;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.api.APITransaction;
import com.hendrianto.uts_petshop.entity.Shop;
import com.hendrianto.uts_petshop.entity.Transaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.android.volley.Request.Method.DELETE;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.adapterUserViewHolder>{
    private List<Transaction> transactionList;
    private Context context;
    private View view;

    public TransactionAdapter(Context context,List<Transaction> transactionList){
        this.context = context;
        this.transactionList = transactionList;
    }
    @NonNull
    @Override
    public adapterUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.activity_adapter_transaction, parent, false);
        return new TransactionAdapter.adapterUserViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adapterUserViewHolder holder, int position) {
        final Transaction transaction = transactionList.get(position);

        holder.namaItem.setText(transaction.getNama_barang());
        holder.hargaDanSatuan.setText(transaction.getJumlah()+" x Rp. "+transaction.getHarga());
        holder.email.setText("Buyed by : "+transaction.getEmail());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TransactionActivity.class);
                intent.putExtra("status","edit");
                intent.putExtra("sendid", transaction.getId());
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                deleteData(transaction.getId());
                                Intent intent = new Intent(context, TransactionDataActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                ((Activity)context).finish();
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


    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class adapterUserViewHolder extends RecyclerView.ViewHolder {
        private TextView namaItem,hargaDanSatuan,email;
        private Button edit,delete;

        public adapterUserViewHolder(@NonNull View itemView) {
            super(itemView);
            namaItem         = (TextView) itemView.findViewById(R.id.nama_item);
            hargaDanSatuan        = (TextView) itemView.findViewById(R.id.harga_dan_satuan);
            email         =(TextView) itemView.findViewById(R.id.email_txt);
            edit = (Button) itemView.findViewById(R.id.btnEdit);
            delete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }

    public void deleteData(int npm){
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(DELETE, APITransaction.URL_DELETE+npm , new Response.Listener<String>() {
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
