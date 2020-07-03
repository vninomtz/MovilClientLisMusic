package com.uv.lismusicjava.Account;

import androidx.lifecycle.MutableLiveData;

import com.uv.lismusicjava.services.WriterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private static AccountRepository accountRepository;
    MutableLiveData<Account> accountData  = new MutableLiveData<>();
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

    public MutableLiveData<Account> saveAccountData(Account account){
        accountApi.saveAccount(account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    accountData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                accountData.setValue(null);
            }
        });
        return accountData;
    }

}
