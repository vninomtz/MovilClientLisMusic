package com.uv.lismusicjava.domain;

public class Gender {
    private String genderName;
    private int coverGender;

    public Gender(String genderName, int coverGender) {
        this.genderName = genderName;
        this.coverGender = coverGender;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public int getCoverGender() {
        return coverGender;
    }

    public void setCoverGender(int coverGender) {
        this.coverGender = coverGender;
    }
}
