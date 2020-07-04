package com.uv.lismusicjava.pojo;

public class Account {
    private String idAccount;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String gender;
    private String cover;
    private String created;
    private String updated;
    private String birthday;
    private Boolean contentCreator;
    private String accesToken;

    public Account(String idAccount, String firstName, String lastName, String email, String userName,
                   String gender, String cover, String created, String updated, String birthday,
                   Boolean contentCreator, String accesToken) {
        this.idAccount = idAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.gender = gender;
        this.cover = cover;
        this.created = created;
        this.updated = updated;
        this.birthday = birthday;
        this.contentCreator = contentCreator;
        this.accesToken = accesToken;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getContentCreator() {
        return contentCreator;
    }

    public void setContentCreator(Boolean contentCreator) {
        this.contentCreator = contentCreator;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }
}
