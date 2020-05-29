package com.uv.lismusicjava.domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private String owner;
    private String image;
    public List<Track> trackList;

    public Playlist(int id, String name, String owner, String image, List<Track> trackList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.image = image;
        this.trackList = trackList;
    }

    public Track getTrack(int position){
        return trackList.get(position);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
