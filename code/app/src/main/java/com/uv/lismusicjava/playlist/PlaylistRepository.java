package com.uv.lismusicjava.playlist;

import androidx.lifecycle.MutableLiveData;

import com.uv.lismusicjava.services.ReaderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistRepository {
    private static PlaylistRepository playlistRepository;
    MutableLiveData<List<Playlist>> playlistData = new MutableLiveData<>();
    private PlaylistApi playlistApi;
    public static PlaylistRepository getInstance(){
        if(playlistRepository == null){
            playlistRepository = new PlaylistRepository();
        }
        return playlistRepository;
    }
    public PlaylistRepository(){
        playlistApi = ReaderService.createService(PlaylistApi.class);
    }
    public MutableLiveData<List<Playlist>> getPlaylistAccount(String idAccount){

        playlistApi.getPlaylistsAccount(idAccount).enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                if(response.isSuccessful()){
                    playlistData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                playlistData.setValue(null);
            }
        });
        return playlistData;
    }
}
