package com.uv.lismusicjava.artist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Artist implements Parcelable {
    @SerializedName("idArtist")
    private String idArtist;
    @SerializedName("name")
    private String name;
    @SerializedName("cover")
    private String cover;
    @SerializedName("registerDate")
    private String registerDate;
    @SerializedName("description")
    private String description;

    public Artist(String idArtist, String name, String cover, String registerDate, String description) {
        this.idArtist = idArtist;
        this.name = name;
        this.cover = cover;
        this.registerDate = registerDate;
        this.description = description;
    }

    protected Artist(Parcel in) {
        idArtist = in.readString();
        name = in.readString();
        cover = in.readString();
        registerDate = in.readString();
        description = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    public String getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idArtist);
        dest.writeString(name);
        dest.writeString(cover);
        dest.writeString(registerDate);
        dest.writeString(description);
    }
}
