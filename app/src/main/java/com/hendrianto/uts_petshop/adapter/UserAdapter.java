package com.hendrianto.uts_petshop.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.hendrianto.uts_petshop.R;
import com.hendrianto.uts_petshop.ShopDataActivity;
import com.hendrianto.uts_petshop.UserDataActivity;
import com.hendrianto.uts_petshop.api.APIShop;
import com.hendrianto.uts_petshop.api.APIUser;
import com.hendrianto.uts_petshop.entity.Cart;
import com.hendrianto.uts_petshop.entity.Users;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.android.volley.Request.Method.DELETE;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.adapterUserViewHolder> {
    private List<Users> dataList;
    private Context context;
    private View view;

    public UserAdapter(Context context, List<Users> dataList){
        this.dataList = dataList;
        this.context = context;
    }
    @NonNull
    @Override
    public UserAdapter.adapterUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.activity_adapter_user, parent, false);
        return new UserAdapter.adapterUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.adapterUserViewHolder holder, int position) {
        final Users cart = dataList.get(position);

        holder.txtNama.setText(cart.getNama());
        holder.txtEmail.setText(cart.getEmail());
        holder.txtTelp.setText(cart.getTelp());
        holder.ivHapus.setOnClickListener(new View.OnClickListener() {
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
                                deleteData(cart.getId());
                                Intent intent = new Intent(context, UserDataActivity.class);
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

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class adapterUserViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtTelp, txtEmail,ivHapus;
        public adapterUserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama         = (TextView) itemView.findViewById(R.id.txtNama);
            txtTelp        = (TextView) itemView.findViewById(R.id.txtTelp);
            txtEmail         = (TextView) itemView.findViewById(R.id.txtEmail);
            ivHapus         = (TextView) itemView.findViewById(R.id.ivHapus);
        }
    }
    public void deleteData(int npm){
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(DELETE, APIUser.URL_DELETE+npm , new Response.Listener<String>() {
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
