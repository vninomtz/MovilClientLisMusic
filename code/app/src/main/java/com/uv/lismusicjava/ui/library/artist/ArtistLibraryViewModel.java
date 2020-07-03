package com.uv.lismusicjava.ui.library.artist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.artist.Artist;
import com.uv.lismusicjava.artist.ArtistRepository;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.List;

public class ArtistLibraryViewModel extends ViewModel {
    private String idAccount = SingletonAccount.getSingletonAccount().getIdAccount();
    private MutableLiveData<List<Artist>> mutableLiveData;
    private ArtistRepository artistRepository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        artistRepository = ArtistRepository.getInstance();
        mutableLiveData = artistRepository.getPlaylistAccount(idAccount);
    }

    public LiveData<List<Artist>> getArtistsRepository(){
        return mutableLiveData;
    }
}
