package com.uv.lismusicjava.domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int idPlaylist;
    private String title;
    private String idAccount;
    private String cover;
    public List<Track> trackList;

    public Playlist(int idPlaylist, String title, String idAccount, String cover, List<Track> trackList) {
        this.idPlaylist = idPlaylist;
        this.title = title;
        this.idAccount = idAccount;
        this.cover = cover;
        this.trackList = trackList;
    }

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

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
