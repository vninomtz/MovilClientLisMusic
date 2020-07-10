package com.uv.lismusicjava;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uv.lismusicjava.databinding.ActivityHomeBinding;
import com.uv.lismusicjava.player.PlayerViewModel;
import com.uv.lismusicjava.ui.login.LoginActivity;
import com.uv.lismusicjava.utils.SingletonAccount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding homeBinding;
    PlayerViewModel playerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding= DataBindingUtil.setContentView(this, R.layout.activity_home);

        if(AccessToken.getCurrentAccessToken() == null && SingletonAccount.getSingletonAccount() == null ){
            goLoginScreen();
        }else{
            BottomNavigationView navView = findViewById(R.id.nav_view);
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_search, R.id.navigation_library)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);
            //Toast.makeText(this, "Welcome " + SingletonAccount.getSingletonAccount().getFirstName(), Toast.LENGTH_SHORT).show();
            playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

            MediaPlayer mp = MediaPlayer.create(this, R.raw.track);

            homeBinding.musicplayer.imageViewPlayPause.setOnClickListener(v -> {
                if(mp.isPlaying()){
                    mp.pause();
                    Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
                    homeBinding.musicplayer.imageViewPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else{
                    mp.start();
                    Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
                    homeBinding.musicplayer.imageViewPlayPause.setImageResource(R.drawable.ic_baseline_pause_24);
                }

            });
        }
    }



    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void LogoutWithFacebook(View view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

}
