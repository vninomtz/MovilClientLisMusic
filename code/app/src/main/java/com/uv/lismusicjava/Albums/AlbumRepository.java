package com.uv.lismusicjava.Albums;
import androidx.lifecycle.MutableLiveData;
import com.uv.lismusicjava.services.ReaderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumRepository {
    private static AlbumRepository albumRepository;
    MutableLiveData<List<Album>> albumData = new MutableLiveData<>();
    private AlbumsApi albumsApi;

    public static  AlbumRepository getInstance(){
        if(albumRepository == null){
            albumRepository = new AlbumRepository();
        }
        return  albumRepository;
    }

    public AlbumRepository(){
        albumsApi = ReaderService.createService(AlbumsApi.class);
    }

    public MutableLiveData<List<Album>> getAlbumLikesListAccount(String idAccount, String token){
        albumsApi.getAlbumsLikes(idAccount, token).enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if(response.isSuccessful()){
                    albumData.setValue(response.body());
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                albumData.setValue(null);
            }
        });
        return albumData;
    }

}
