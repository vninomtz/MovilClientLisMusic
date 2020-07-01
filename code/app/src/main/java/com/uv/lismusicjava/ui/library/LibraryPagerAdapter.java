package com.uv.lismusicjava.ui.library;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.uv.lismusicjava.R;

public class LibraryPagerAdapter extends FragmentStatePagerAdapter {

    LibraryPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new PlaylistLibraryFragment();
                return fragment;
            case 1:
                fragment = new ArtistLibraryFragment();
                return fragment;
            case 2:
                fragment = new AlbumLibraryFragment();
                return fragment;

        }
        return null;

        /*Bundle args = new Bundle();
        args.putInt(PlaylistLibraryFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);*/
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Playlists";
            case 1:
                return "Artists";
            case 2:
                return "Albums";
        }
        return null;
    }
}
