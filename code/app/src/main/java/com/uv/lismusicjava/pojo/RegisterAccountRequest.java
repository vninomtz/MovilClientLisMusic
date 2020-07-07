package com.uv.lismusicjava.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterAccountRequest {

    @SerializedName("email")
    private String email;
    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("gender")
    private String gender;
    @SerializedName("cover")
    private String cover;
    @SerializedName("typeRegister")
    private String typeRegister;

    public RegisterAccountRequest(String firstName, String lastName,String email,String userName, String password,
                                  String gender,String cover, String birthday, String typeRegister){
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.cover = cover;
        this.typeRegister = typeRegister;

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTypeRegister() {
        return typeRegister;
    }

    public void setTypeRegister(String typeRegister) {
        this.typeRegister = typeRegister;
    }
}