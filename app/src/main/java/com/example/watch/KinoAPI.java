package com.example.watch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KinoAPI
{
    @GET("movies?filter=new")
    Call<List<KinoParam>> getData();
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://cinema.areas.su/").addConverterFactory(GsonConverterFactory.create()).build();

    @POST("auth/login")
    Call<User> postAuth(@Body User user);
}
