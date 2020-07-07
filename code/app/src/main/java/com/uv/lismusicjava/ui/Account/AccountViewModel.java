package com.uv.lismusicjava.ui.Account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.Account.Account;
import com.uv.lismusicjava.Account.AccountRepository;
import com.uv.lismusicjava.pojo.RegisterAccountRequest;
import com.uv.lismusicjava.pojo.RegisterAccountResponse;

public class AccountViewModel extends ViewModel {
    private MutableLiveData<RegisterAccountResponse> registerAccountObserver;
    private MutableLiveData<String> registerAccountError = new MutableLiveData<>();
    private AccountRepository accountRepository;
    private MutableLiveData<String> toastMessageObserver = new MutableLiveData<>();


    public void init(){
        if(registerAccountObserver != null){
            return;
        }
        accountRepository = AccountRepository.getInstance();
    }
    public LiveData<String> getToastObserver(){
        return  toastMessageObserver;
    }

    public LiveData<RegisterAccountResponse> getRegisterAccountResponse(){
        return registerAccountObserver;
    }
    public LiveData<String> getRegisterAccountError(){
        return registerAccountError;
    }

    public boolean validateFieldsRegister(Account account){
        if(account.getEmail().isEmpty()){
            toastMessageObserver.setValue("Please enter an email");
            return false;
        }
        if(account.getUsername().isEmpty()){
            toastMessageObserver.setValue("Please enter an username");
            return false;
        }
        if(account.getPassword().isEmpty()){
            toastMessageObserver.setValue("Please enter a password");
            return false;
        }
        if(account.getFirstName().isEmpty()){
            toastMessageObserver.setValue("Please enter a first name");
            return false;
        }
        if(account.getLastName().isEmpty()){
            toastMessageObserver.setValue("Please enter a last name");
            return false;
        }
        if(account.getGender().isEmpty()){
            toastMessageObserver.setValue("Please select a gender");
            return false;
        }
        if(account.getBirthDate().isEmpty()){
            toastMessageObserver.setValue("Please choose a birthday");
            return false;
        }
        registerAccountObserver = accountRepository.saveAccountData(new RegisterAccountRequest(account.getFirstName(),
                account.getLastName(),account.getEmail(),account.getUsername(), account.getPassword(), account.getGender(),
                account.getCover(),account.getBirthDate(), account.getSocialMedia()));
        registerAccountError = accountRepository.getRegisterAccountError();
        return  true;
    }

}
