package com.example.ramir.studentapp.client;

import com.example.ramir.studentapp.map.Graph;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarpoolingService {

    @GET("/map")
    Call<Graph> getMap();
}
