package com.uv.lismusicjava.ui.library.artistalbums;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.Albums.Album;
import com.uv.lismusicjava.Albums.AlbumRepository;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.List;

public class ArtistAlbumViewModel extends ViewModel {
    private String idArtist = SingletonAccount.getSingletonAccount().getIdAccount();
    private String token = SingletonAccount.getSingletonAccount().getAccesToken();
    private MutableLiveData<List<Album>> albumsOfArtist;
    private MutableLiveData<String> albumOfArtistError = new MutableLiveData<>();
    private AlbumRepository albumRepository;


    public void init(String idArtist){
        if(albumsOfArtist != null){
            return;
        }
        albumRepository = AlbumRepository.getInstance();
        albumsOfArtist = albumRepository.getAlbumsOfArtist(idArtist,token);
        albumOfArtistError = albumRepository.getAlbumsOfArtistError();
    }

    public MutableLiveData<List<Album>> getAlbumsOfArtist(){
        return albumsOfArtist;
    }

    public MutableLiveData<String> getAlbumOfArtistError(){
        return albumOfArtistError;
    }


}