package com.uv.lismusicjava.Albums;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.uv.lismusicjava.services.ReaderService;
import com.uv.lismusicjava.track.Track;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumRepository {
    private static AlbumRepository albumRepository;
    MutableLiveData<List<Album>> albumData = new MutableLiveData<>();

    MutableLiveData<String> albumsOfArtistError;
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
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                albumData.setValue(null);
            }
        });
        return albumData;
    }

    public MutableLiveData<List<Album>> getAlbumsOfArtist(String idArtist, String token){
        MutableLiveData<List<Album>> albumsOfArtistLiveData = new MutableLiveData<>();
        albumsApi.getAlbumsOfArtist(idArtist,token).enqueue(new Callback<List<Album>>() {

            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if(response.isSuccessful()){
                    albumsOfArtistLiveData.setValue(response.body());
                    System.out.println("Operación exitosa: ");
                    System.out.println(response.body().get(0).getTitle());
                } else {
                    System.out.println("Ocurrió un error con la petición");
                    JSONObject jObjError = null;
                    try {
                        jObjError = new JSONObject(response.errorBody().string());
                        System.out.println(jObjError.getString("error"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                albumsOfArtistError.setValue("Connection error, please try again");

            }
        });
        return  albumsOfArtistLiveData;

    }


    public MutableLiveData<String> getAlbumsOfArtistError(){
        albumsOfArtistError = new MutableLiveData<>();
        return albumsOfArtistError;
    }




}
