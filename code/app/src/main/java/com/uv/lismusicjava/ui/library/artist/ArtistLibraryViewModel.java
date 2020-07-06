package com.uv.lismusicjava.ui.library.artist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.artist.Artist;
import com.uv.lismusicjava.artist.ArtistRepository;
import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.playlist.PlaylistRepository;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.List;

public class ArtistLibraryViewModel extends ViewModel {
    private String idAccount = SingletonAccount.getSingletonAccount().getIdAccount();
    private MutableLiveData<List<Artist>> artistsLiveData;
    private ArtistRepository artistRepository;
    private  MutableLiveData<String> artistError = new MutableLiveData<>();

    public void init(){
        if(artistsLiveData != null){
            return;
        }
        artistRepository = ArtistRepository.getInstance();
        artistsLiveData = artistRepository.getArtistLikeofAccount(idAccount);
        artistError = artistRepository.getArtistError();
    }
    public LiveData<List<Artist>> getArtistsLiveData(){
        return artistsLiveData;
    }
    public LiveData<String> getArtistsError(){
        return artistError;
    }
}
