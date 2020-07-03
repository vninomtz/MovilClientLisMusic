package com.uv.lismusicjava.artist;

import androidx.lifecycle.MutableLiveData;

import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.playlist.PlaylistApi;
import com.uv.lismusicjava.playlist.PlaylistRepository;
import com.uv.lismusicjava.services.ReaderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistRepository {
    private static ArtistRepository artistRepository;
    MutableLiveData<List<Artist>> artistData = new MutableLiveData<>();
    private ArtistApi artistApi;

    public static ArtistRepository getInstance(){
        if(artistRepository == null){
            artistRepository = new ArtistRepository();
        }
        return artistRepository;
    }
    public ArtistRepository(){
        artistApi = ReaderService.createService(ArtistApi.class);
    }
    public MutableLiveData<List<Artist>> getPlaylistAccount(String idAccount){

        artistApi.getArtistsLikeOfAccount(idAccount).enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                if(response.isSuccessful()){
                    artistData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                artistData.setValue(null);
            }
        });
        return artistData;
    }
}



