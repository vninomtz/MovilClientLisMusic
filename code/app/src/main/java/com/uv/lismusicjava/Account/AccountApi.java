package com.uv.lismusicjava.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("account")
    @FormUrlEncoded
    Call<Account> saveAccount(@Body Account account);
}
