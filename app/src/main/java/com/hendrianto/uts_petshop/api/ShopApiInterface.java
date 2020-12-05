package com.hendrianto.uts_petshop.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShopApiInterface {
    @GET("pet")
    Call<ShopResponse> getAllShop(@Query("data")String data);

    @GET("pet/{id}")
    Call<ShopResponse> getShopById(@Path("id") int id,
                                   @Query("data")String data);
}
