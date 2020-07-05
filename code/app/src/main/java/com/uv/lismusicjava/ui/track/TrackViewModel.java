package com.uv.lismusicjava.ui.track;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.track.Track;
import com.uv.lismusicjava.track.TrackRepository;

import java.util.List;

public class TrackViewModel extends ViewModel {
    private MutableLiveData<List<Track>> listTracks;
    private TrackRepository trackRepository;
    public void init(){
        if(listTracks != null){
            return;
        }
        trackRepository = TrackRepository.getInstance();
    }
    public LiveData<List<Track>> getTracksPlaylist(int idPlaylist){
        if(listTracks == null){
            listTracks = new MutableLiveData<>();
            loadTracks(idPlaylist);
        }
        return listTracks;
    }

    private void loadTracks(int idPlaylist) {
        listTracks = trackRepository.getTracksPlaylist(idPlaylist);
    }
}