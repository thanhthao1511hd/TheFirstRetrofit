package com.example.thefirstretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getAPI {
    @GET("changes/")
    Call<List<Name>> loadChanges(@Query("q") String status);
}
