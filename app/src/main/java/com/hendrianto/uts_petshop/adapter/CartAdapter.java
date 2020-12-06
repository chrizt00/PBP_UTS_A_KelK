package com.hendrianto.uts_petshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hendrianto.uts_petshop.R;
import com.hendrianto.uts_petshop.entity.Cart;
import com.hendrianto.uts_petshop.entity.Shop;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.adapterUserViewHolder>{
    private List<Cart> dataList;
    private Context context;
    private View view;

    public CartAdapter(Context context,List<Cart> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public adapterUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.activity_adapter_cart, parent, false);
        return new CartAdapter.adapterUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterUserViewHolder holder, int position) {
        final Cart cart = dataList.get(position);

        holder.txtNama.setText(cart.getNama_barang());
        holder.txtJenis.setText(cart.getJenis());
        holder.txtHarga.setText("Rp"+String.valueOf(cart.getHarga()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class adapterUserViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtHarga, txtJenis;
        public adapterUserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama         = (TextView) itemView.findViewById(R.id.txtNama);
            txtHarga        = (TextView) itemView.findViewById(R.id.txtHarga);
            txtJenis         =(TextView) itemView.findViewById(R.id.txtJenis);
        }
    }
}
