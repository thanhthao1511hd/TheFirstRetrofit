package com.example.thefirstretrofit;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Name>> {
    static final String BASE_URL="https://git.eclipse.org/r/";
    public void start()
    {
        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        getAPI api=retrofit.create(getAPI.class);
        Call<List<Name>> call=api.loadChanges("status:open");
        call.enqueue(this);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
        if (response.isSuccessful()){
            List<Name> chanList=response.body();
            chanList.forEach(changes -> System.out.println(changes.name));
        }else{
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Name>> call, Throwable t) {
        t.printStackTrace();
    }
}
