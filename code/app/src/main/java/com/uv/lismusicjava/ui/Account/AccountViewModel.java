package com.uv.lismusicjava.ui.Account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.Account.Account;
import com.uv.lismusicjava.Account.AccountRepository;

public class AccountViewModel extends ViewModel {
    private MutableLiveData<Account> mutableLiveData;
    private AccountRepository accountRepository;
    private Account account;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        accountRepository = AccountRepository.getInstance();
        mutableLiveData = accountRepository.saveAccountData(account);
    }
    public LiveData<Account> saveAccountRepository(){
        return mutableLiveData;
    }
}
