package com.uv.lismusicjava.utils;

import com.uv.lismusicjava.pojo.Account;

public class SingletonAccount {
    private static Account singletonAccount;

    private SingletonAccount(){}

    public static void setSingletonAccount(Account account){
        if (singletonAccount == null){
            singletonAccount = account;
        }
    }

    public static Account getSingletonAccount(){
        return singletonAccount;
    }
}
