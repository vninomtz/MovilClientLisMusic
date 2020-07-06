package com.uv.lismusicjava.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.Account.AccountRepository;
import com.uv.lismusicjava.pojo.LoginRequest;
import com.uv.lismusicjava.pojo.LoginResponse;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginResponse> loginObserver;
    private MutableLiveData<String> loginError = new MutableLiveData<>();
    private MutableLiveData<String> toastMessageObserver = new MutableLiveData<>();
    private AccountRepository accountRepository;

    public void init(){
        if(loginObserver != null){
            return;
        }

        accountRepository = AccountRepository.getInstance();
    }

    public LiveData<String> getToastObserver(){
        return toastMessageObserver;
    }

    public void validateFieldsLogin( String user, String password){
        if(user.isEmpty()){
            toastMessageObserver.setValue("Please enter an username or email");
        } else if(password.isEmpty()){
            toastMessageObserver.setValue("Please enter a password");
        } else {
            loginObserver = accountRepository.loginAccount(new LoginRequest(user,password));
            loginError = accountRepository.getLoginError();
        }
    }

    public LiveData<LoginResponse> getLoginResponse(){
        return loginObserver;
    }

    public LiveData<String> getLoginError(){
        return loginError;
    }

}
