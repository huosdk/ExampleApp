package com.huosdk.exampleapp.common.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuhongliang on 2017/10/31.
 */

public class RetrofitClient {
    private static Retrofit retrofit;
    public static synchronized Retrofit getRetrofit(){
        if(retrofit==null){
            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor()).build();
            retrofit=new Retrofit.Builder()
                    .baseUrl("https://api.9isy.com/api/v7/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
