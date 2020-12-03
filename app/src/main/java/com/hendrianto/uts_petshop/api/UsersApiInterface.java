package com.hendrianto.uts_petshop.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsersApiInterface {
    @GET("loginpbp")
    Call<UserResponse> getAllUser(@Query("data")String data);

    @GET("loginpbp/{id}")
    Call<UserResponse> getUserById(@Path("id") int id,
                                   @Query("data")String data);

    @POST("loginpbp")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("email")String email,
                                  @Field("password")String password,
                                  @Field("nama")String nama,
                                  @Field("telp")String telp,
                                  @Field("verifCode")String verifCode,
                                  @Field("isVerified")int isVerified);
}
