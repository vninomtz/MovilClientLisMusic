package com.uv.lismusicjava.services;

import com.uv.lismusicjava.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReaderService {
    private static String IP = Integer.toString(R.string.ip);
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://" + "10.0.2.2" +":6000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class <S> serviceClass){
        return retrofit.create(serviceClass);
    }

}
