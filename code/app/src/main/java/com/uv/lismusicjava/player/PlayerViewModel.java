package com.uv.lismusicjava.player;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uv.lismusicjava.R;

import java.util.List;
import java.util.Objects;

public class PlayerViewModel extends ViewModel {
    MutableLiveData<List<MediaPlayer>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<MediaPlayer> mediaPlayerMutableLiveData;

    public void init(MediaPlayer mediaPlayer){
        if(mediaPlayer != null){
            mediaPlayerMutableLiveData.setValue(mediaPlayer);
        }
    }
    public boolean isPlaying(){
        return mediaPlayerMutableLiveData.getValue().isPlaying();
    }

    public void Pause(){
        mediaPlayerMutableLiveData.getValue().pause();
    }
    public void Play(){
       mediaPlayerMutableLiveData.getValue().start();
    }
}
