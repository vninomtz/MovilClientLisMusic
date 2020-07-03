package com.uv.lismusicjava.ui.library.Album;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.uv.lismusicjava.Albums.Album;
import com.uv.lismusicjava.Albums.AlbumRepository;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.List;

public class AlbumLibraryViewModel extends ViewModel {
    private String idAccount = SingletonAccount.getSingletonAccount().getIdAccount();
    private String tokenAccount = SingletonAccount.getSingletonAccount().getAccesToken();
    private MutableLiveData<List<Album>> mutableLiveData;
    private AlbumRepository albumRepository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        albumRepository = AlbumRepository.getInstance();
        mutableLiveData = albumRepository.getAlbumLikesListAccount(idAccount,tokenAccount);
    }
    public LiveData<List<Album>> getAlbumsRepository(){
        return  mutableLiveData;
    }
}
