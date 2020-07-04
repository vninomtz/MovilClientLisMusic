package com.uv.lismusicjava.services;


import com.uv.lismusicjava.domain.Account;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WriterService {
    private static String IP ="10.0.2.2";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://"+ IP+ ":5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

}
