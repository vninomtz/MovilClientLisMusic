package com.uv.lismusicjava.player;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.uv.lismusicjava.R;

public class PlayerViewState {
    private PlayerState playerState;

    public PlayerViewState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public String getPlayerState() {
        if (playerState == PlayerState.PLAYING) {
            return "Playing";
        }
        if(playerState.equals(PlayerState.PAUSED)) {
            return "Paused";
        }
        if(playerState == PlayerState.ERROR){
            return "Error";
        }
        return "";
    }
    public Drawable getPlayPauseIcon(Context context){
        if(playerState == PlayerState.PLAYING){
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_pause_24);
        }else{
            return ContextCompat.getDrawable(context, R.drawable.ic_baseline_play_arrow_24);
        }
    }

    public boolean getPlayPauseIconClicable(){
        if(playerState == PlayerState.PLAYING){
            return true;
        }
        if(playerState == PlayerState.PAUSED){
            return true;
        }
        return false;
    }
}
