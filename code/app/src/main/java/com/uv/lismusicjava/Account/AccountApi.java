package com.uv.lismusicjava.Account;

import com.uv.lismusicjava.pojo.LoginRequest;
import com.uv.lismusicjava.pojo.LoginResponse;
import com.uv.lismusicjava.pojo.RegisterAccountRequest;
import com.uv.lismusicjava.pojo.RegisterAccountResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("account")
    Call<RegisterAccountResponse> saveAccount(@Body RegisterAccountRequest registerAccountRequest);

    @POST("login")
    Call<LoginResponse> loginAccount(@Body LoginRequest loginRequest);


}
