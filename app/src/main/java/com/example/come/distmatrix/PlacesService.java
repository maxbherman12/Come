package com.example.come.distmatrix;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesService {
    @GET("/maps/api/distancematrix/json")
    Call<Root> queryDistance(@Query("origins") String origin,
                             @Query("destinations") String destination,
                             @Query("key") String key);
}

