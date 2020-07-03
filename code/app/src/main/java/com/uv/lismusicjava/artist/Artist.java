package com.uv.lismusicjava.artist;

import com.google.gson.annotations.SerializedName;

public class Artist {
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
}
