package com.uv.lismusicjava.domain;

import java.util.Date;

public class Account {
    private  int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String gender;
    private String cover;
    private Date createdDate;
    private Date updatedDate;
    private Boolean contentCreator;
    public SocialMedia socialMedia;

    public Account(int id, String firstName, String lastName, String email, String username,
                   String password, String gender, String cover, Date createdDate, Boolean
                           contentCreator, SocialMedia socialMedia) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.cover = cover;
        this.createdDate = createdDate;
        this.contentCreator = contentCreator;
        this.socialMedia = socialMedia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getContentCreator() {
        return contentCreator;
    }

    public void setContentCreator(Boolean contentCreator) {
        this.contentCreator = contentCreator;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }
}