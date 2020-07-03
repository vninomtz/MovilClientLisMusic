package com.uv.lismusicjava.track;

public class Track {
    private String idTrack;
    private String title;
    private float duration;
    private String fileTrack;
    private boolean avaible;
    private String cover;
    private String artistName;

    public Track(String idTrack, String title, float duration, String fileTrack, boolean avaible, String cover, String artistName) {
        this.idTrack = idTrack;
        this.title = title;
        this.duration = duration;
        this.fileTrack = fileTrack;
        this.avaible = avaible;
        this.cover = cover;
        this.artistName = artistName;
    }

    public String getIdTrack() {
        return idTrack;
    }

    public void setIdTrack(String idTrack) {
        this.idTrack = idTrack;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getFileTrack() {
        return fileTrack;
    }

    public void setFileTrack(String fileTrack) {
        this.fileTrack = fileTrack;
    }

    public boolean isAvaible() {
        return avaible;
    }

    public void setAvaible(boolean avaible) {
        this.avaible = avaible;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
