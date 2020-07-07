package com.uv.lismusicjava.Account;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uv.lismusicjava.pojo.LoginRequest;
import com.uv.lismusicjava.pojo.LoginResponse;
import com.uv.lismusicjava.pojo.RegisterAccountRequest;
import com.uv.lismusicjava.pojo.RegisterAccountResponse;
import com.uv.lismusicjava.services.WriterService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private static AccountRepository accountRepository;
    MutableLiveData<RegisterAccountResponse> accountData  = new MutableLiveData<>();
    MutableLiveData<String> accountError  = new MutableLiveData<>();
    MutableLiveData<String> loginError = new MutableLiveData<>();


    private AccountApi accountApi;

    public static AccountRepository getInstance(){
        if(accountRepository == null){
            accountRepository = new AccountRepository();
        }
        return accountRepository;
    }

    public AccountRepository(){
        accountApi = WriterService.createService(AccountApi.class);
    }

    public MutableLiveData<RegisterAccountResponse> saveAccountData(RegisterAccountRequest registerAccountRequest){
        accountApi.saveAccount(registerAccountRequest).enqueue(new Callback<RegisterAccountResponse>() {
            @Override
            public void onResponse(Call<RegisterAccountResponse> call, Response<RegisterAccountResponse> response) {
                if(response.isSuccessful()){
                    accountData.setValue(response.body());
                }else{
                    try{
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        accountError.setValue(jsonObject.getString("error"));
                    }catch (JSONException | IOException e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<RegisterAccountResponse> call, Throwable t) {
                accountError.setValue("Connection error, please try again");
            }
        });
        return accountData;
    }
    public MutableLiveData<String> getRegisterAccountError(){
        return  accountError;
    }

    public MutableLiveData<LoginResponse> loginAccount(LoginRequest loginRequest){
        MutableLiveData<LoginResponse> loginData  = new MutableLiveData<>();
        accountApi.loginAccount(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginData.setValue(response.body());

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        loginError.setValue(jObjError.getString("error"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginError.setValue("Connection error, please try again");
            }
        });
        return loginData;
    }

    public MutableLiveData<String> getLoginError(){
        return loginError;
    }

}
