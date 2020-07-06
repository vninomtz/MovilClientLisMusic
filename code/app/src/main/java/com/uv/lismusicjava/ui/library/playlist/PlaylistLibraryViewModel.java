package com.uv.lismusicjava.ui.library.playlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.playlist.PlaylistRepository;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.List;

public class PlaylistLibraryViewModel extends ViewModel {
    private String idAccount = SingletonAccount.getSingletonAccount().getIdAccount();
    private MutableLiveData<List<Playlist>> mutableLiveData = new MutableLiveData<>();
    private  MutableLiveData<String> playlistError;
    private PlaylistRepository playlistRepository;

    public void init(){
       if(playlistRepository != null){
            return;
        }
        playlistRepository = PlaylistRepository.getInstance();
        mutableLiveData = playlistRepository.getPlaylistAccount(idAccount);
        playlistError = playlistRepository.getPlaylistError();
    }


    public LiveData<List<Playlist>> getPlaylistRepository(){
        return mutableLiveData;
    }
    public LiveData<String> getPlaylistError(){
        return playlistError;
    }
}
