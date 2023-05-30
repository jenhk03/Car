package com.example.car.API;

import com.example.car.Model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData
{
    @GET("retrieve1.php")
    Call<ResponseModel> ardRetrieve();
    @FormUrlEncoded
    @POST("create1.php")
    Call<ResponseModel> ardCreate(
            @Field("name") String name,
            @Field("country") String country,
            @Field("est") String est,
            @Field("founder") String founder,
            @Field("description") String description
    );
    @FormUrlEncoded
    @POST("update1.php")
    Call<ResponseModel> ardUpdate(
            @Field("id") String id,
            @Field("name") String name,
            @Field("country") String country,
            @Field("est") String est,
            @Field("founder") String founder,
            @Field("description") String description
    );
    @FormUrlEncoded
    @POST("delete1.php")
    Call<ResponseModel> ardDelete(@Field("id") String id);
}