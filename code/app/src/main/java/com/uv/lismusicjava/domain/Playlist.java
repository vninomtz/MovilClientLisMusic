package com.uv.lismusicjava.domain;

public class Playlist {
    private int id;
    private String name;
    private String owner;
    private String image;

    public Playlist(int id, String name, String owner, String image) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.image = image;
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
