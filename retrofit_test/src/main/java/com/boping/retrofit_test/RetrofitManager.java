package com.boping.retrofit_test;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public static final String BASE_HTTP_URL = "https://api.github.com/";
    private static Retrofit retrofit;
    private static RetrofitManager retrofitManager;

    private RetrofitManager(Context context) {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_HTTP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    public static RetrofitManager getInstance(Context context) {
        if (retrofitManager == null)
            retrofitManager = new RetrofitManager(context);
        return retrofitManager;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
