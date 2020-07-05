package com.uv.lismusicjava.pojo;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user")
    String user;
    @SerializedName("password")
    String password;

    public LoginRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
