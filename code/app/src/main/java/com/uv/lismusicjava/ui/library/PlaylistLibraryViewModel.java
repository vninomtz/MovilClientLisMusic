package com.uv.lismusicjava.ui.library;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.playlist.PlaylistRepository;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.List;

public class PlaylistLibraryViewModel extends ViewModel {
    private String idAccount = SingletonAccount.getSingletonAccount().getIdAccount();
    private MutableLiveData<List<Playlist>> mutableLiveData;
    private PlaylistRepository playlistRepository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        playlistRepository = PlaylistRepository.getInstance();
        mutableLiveData = playlistRepository.getPlaylistAccount(idAccount);
    }

    public LiveData<List<Playlist>> getPlaylistRepository(){
        return mutableLiveData;
    }
}
