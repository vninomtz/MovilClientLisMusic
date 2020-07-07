package com.uv.lismusicjava.Albums;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Album implements Parcelable {
    @SerializedName("idAlbum")
    private String idAlbum;
    @SerializedName("title")
    private String title;
    @SerializedName("cover")
    private String cover;
    @SerializedName("publication")
    private String publication;
    @SerializedName("recordCompany")
    private String recordCompany;
    @SerializedName("idMusicGender")
    private int idMusicGender;
    @SerializedName("idAlbumType")
    private int idAlbumType;
    @SerializedName("idArtist")
    private String idArtist;

    public Album(String idAlbum, String title, String cover, String publication, String recordCompany,
                 int idMusicGender, int idAlbumType, String idArtist) {
        this.idAlbum = idAlbum;
        this.title = title;
        this.cover = cover;
        this.publication = publication;
        this.recordCompany = recordCompany;
        this.idMusicGender = idMusicGender;
        this.idAlbumType = idAlbumType;
        this.idArtist = idArtist;
    }

    protected Album(Parcel in) {
        idAlbum = in.readString();
        title = in.readString();
        cover = in.readString();
        publication = in.readString();
        recordCompany = in.readString();
        idMusicGender = in.readInt();
        idAlbumType = in.readInt();
        idArtist = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getRecordCompany() {
        return recordCompany;
    }

    public void setRecordCompany(String recordCompany) {
        this.recordCompany = recordCompany;
    }

    public int getIdMusicGender() {
        return idMusicGender;
    }

    public void setIdMusicGender(int idMusicGender) {
        this.idMusicGender = idMusicGender;
    }

    public int getIdAlbumType() {
        return idAlbumType;
    }

    public void setIdAlbumType(int idAlbumType) {
        this.idAlbumType = idAlbumType;
    }

    public String getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idAlbum);
        dest.writeString(title);
        dest.writeString(cover);
        dest.writeString(publication);
        dest.writeString(recordCompany);
        dest.writeInt(idMusicGender);
        dest.writeInt(idAlbumType);
        dest.writeString(idArtist);
    }
}
