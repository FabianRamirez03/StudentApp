package com.example.ramir.studentapp.client;

import com.example.ramir.studentapp.map.Graph;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarpoolingService {

    @GET("map")
    Call<Graph> getMap();

    @GET("users/{user_id}")
    Call<String> getUser(@Path("user_id") int user_id);

}
