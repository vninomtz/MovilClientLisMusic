package com.uv.lismusicjava.track;

import androidx.lifecycle.MutableLiveData;

import com.uv.lismusicjava.services.ReaderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackRepository {
    private static TrackRepository trackRepository;
    MutableLiveData<List<Track>> listMutableLiveData;
    MutableLiveData<String> tracksOfAlbumError;
    private TrackApi trackApi;
    public static TrackRepository getInstance(){
        if(trackRepository == null){
            trackRepository = new TrackRepository();
        }
        return trackRepository;
    }
    public TrackRepository(){
        trackApi = ReaderService.createService(TrackApi.class);
    }

    public MutableLiveData<List<Track>> getTracksPlaylist(int idPlaylist){
        listMutableLiveData = new MutableLiveData<>();
        trackApi.getTracksPlaylist(idPlaylist).enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if(response.isSuccessful()){
                    listMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                listMutableLiveData.setValue(null);
            }
        });
        return listMutableLiveData;
    }

    public MutableLiveData<List<Track>> getTracksOfAlbum(String idAlbum, String token){
        listMutableLiveData = new MutableLiveData<>();
        trackApi.getTracksOfAlbum(idAlbum, token).enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                listMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                tracksOfAlbumError.setValue("Connection error, please try again");
            }
        });

        return listMutableLiveData;
    }

    public MutableLiveData<String> getTracksOfAlbumError(){
        tracksOfAlbumError = new MutableLiveData<String>();
        return tracksOfAlbumError;
    }
}
