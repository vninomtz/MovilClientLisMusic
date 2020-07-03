package com.uv.lismusicjava.playlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Playlist implements Parcelable {
    @SerializedName("idPlaylist")
    private int idPlaylist;
    @SerializedName("title")
    private String title;
    @SerializedName("idAccount")
    private String idAccount;
    @SerializedName("cover")
    private String cover;
    @SerializedName("owner")
    private String owner;
    @SerializedName("creation")
    private Date creation;
    @SerializedName("idPlaylistType")
    private int getIdPlaylistType;
    @SerializedName("publicPlaylist")
    private boolean publicPlaylist;

    public Playlist(int idPlaylist, String title, String idAccount, String cover, String owner, Date creation, int getIdPlaylistType, boolean publicPlaylist) {
        this.idPlaylist = idPlaylist;
        this.title = title;
        this.idAccount = idAccount;
        this.cover = cover;
        this.owner = owner;
        this.creation = creation;
        this.getIdPlaylistType = getIdPlaylistType;
        this.publicPlaylist = publicPlaylist;
    }

    protected Playlist(Parcel in) {
        idPlaylist = in.readInt();
        title = in.readString();
        idAccount = in.readString();
        cover = in.readString();
        owner = in.readString();
        getIdPlaylistType = in.readInt();
        publicPlaylist = in.readByte() != 0;
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public int getGetIdPlaylistType() {
        return getIdPlaylistType;
    }

    public void setGetIdPlaylistType(int getIdPlaylistType) {
        this.getIdPlaylistType = getIdPlaylistType;
    }

    public boolean isPublicPlaylist() {
        return publicPlaylist;
    }

    public void setPublicPlaylist(boolean publicPlaylist) {
        this.publicPlaylist = publicPlaylist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPlaylist);
        dest.writeString(title);
        dest.writeString(idAccount);
        dest.writeString(cover);
        dest.writeString(owner);
        dest.writeInt(getIdPlaylistType);
        dest.writeByte((byte) (publicPlaylist ? 1 : 0));
    }
}
