package com.hendrianto.uts_petshop.adapter;

import android.app.Activity;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.hendrianto.uts_petshop.R;
import com.hendrianto.uts_petshop.entity.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.ShopViewHolder> {
    private Shop[] shops;
    private Activity activity;
    private List<String> title = new ArrayList<>();
    private String jenis;

    public ShopRecyclerViewAdapter(Shop[] shops,Activity activity, String jenis){
        this.shops = shops;
        this.activity = activity;
        this.jenis = jenis;
    }

    public List<String> getTitleChecked(){
        return this.title;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainitem, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        final Shop shop = shops[position];
        holder.textTitle.setText(shop.getNama());
        holder.textPrice.setText("Rp." + String.valueOf(shop.getHarga()));
        Glide.with(activity)
                .load(shop.getImgUrl())
                .apply(new RequestOptions().override(100, 100))
                .centerCrop()
                .into(holder.image);

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.buystatus.getVisibility() == v.INVISIBLE) {
                    setTitleChecked(holder.textTitle.getText().toString());
                    Toast.makeText(v.getContext(), "Buying : " + holder.textTitle.getText().toString(), Toast.LENGTH_SHORT).show();
                    holder.buystatus.setVisibility(v.VISIBLE);
                } else {
                    Toast.makeText(v.getContext(), "Cancelled : " + holder.textTitle.getText().toString(), Toast.LENGTH_SHORT).show();
                    setTitleUnchecked(holder.textTitle.getText().toString());
                    holder.buystatus.setVisibility(v.INVISIBLE);
                }
            }
        });
    }
    public void setTitleChecked(String title){
        this.title.add(title);
    }

    public void setTitleUnchecked(String title){
        for (int i=0; i< this.title.size(); i++){
            if(this.title.get(i).equalsIgnoreCase(title))
            {
                this.title.remove(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return shops.length;
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textTitle;
        private TextView textPrice;
        private TextView buystatus;
        private MaterialCardView itemCard;
        private LinearLayout buy;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.itemImg);
            textTitle =itemView.findViewById(R.id.itemTitle);
            textPrice =itemView.findViewById(R.id.itemPrice);
            buystatus =itemView.findViewById(R.id.buystatus);
            itemCard = itemView.findViewById(R.id.item_cards);
            buy = itemView.findViewById(R.id.buy);
        }
    }
}
