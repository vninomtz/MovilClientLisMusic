package com.uv.lismusicjava.ui.library.albumtracks;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.track.Track;
import com.uv.lismusicjava.track.TrackRepository;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.List;

public class AlbumTracksViewModel extends ViewModel {
    private String token = SingletonAccount.getSingletonAccount().getAccesToken();
    private MutableLiveData<List<Track>> tracksLiveData;
    private MutableLiveData<String> trackOfAlbumError;
    private TrackRepository trackRepository;

    public void init(String idAlbum){
        if(tracksLiveData != null){
            return;
        }

        trackRepository = TrackRepository.getInstance();
        tracksLiveData = trackRepository.getTracksOfAlbum(idAlbum,token);
        trackOfAlbumError = trackRepository.getTracksOfAlbumError();

    }

    public MutableLiveData<List<Track>> getTracksOfAlbumLiveData() {
        return tracksLiveData;
    }

    public MutableLiveData<String> getTrackOfAlbumError() {
        return trackOfAlbumError;
    }
}