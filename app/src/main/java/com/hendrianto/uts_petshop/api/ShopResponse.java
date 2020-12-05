package com.hendrianto.uts_petshop.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hendrianto.uts_petshop.entity.Shop;

import java.util.List;

public class ShopResponse {
    @SerializedName("data")
    @Expose
    private List<Shop> Shop = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<Shop> getShop(){
        return Shop;
    }

    public String getMessage(){
        return message;
    }

    public void setShop(List<Shop> Shop){
        this.Shop = Shop;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
