package com.example.thoitiet.api;

import com.example.thoitiet.Model.DataResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("forecast")
    Call<DataResponse> getWeather(@Query("q") String q,
                                  @Query("appid") String appid,
                                  @Query("lang") String lang,
                                  @Query("units") String units,
                                  @Query("cnt") String cnt);


}
