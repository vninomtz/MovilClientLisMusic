package com.uv.lismusicjava.ui.library.albumtracks;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uv.lismusicjava.Albums.Album;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.artist.Artist;
import com.uv.lismusicjava.track.Track;

import java.util.ArrayList;

public class AlbumTracksFragment extends Fragment {

    private AlbumTracksViewModel albumTracksViewModel;
    private RecyclerView recyclerView;
    private ArrayList<Track> listTracks = new ArrayList<>();
    private Album album;
    private Artist artist;
    private ImageView image_albumCover;
    private TextView textView_albumTitle;
    private TextView textView_artistName;
    private TextView textView_genderName;


    public static AlbumTracksFragment newInstance() {
        return new AlbumTracksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_album_tracks, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_tracksAlbums);
        textView_albumTitle = view.findViewById(R.id.textView_title_fragment_albumTracks);
        textView_genderName = view.findViewById(R.id.textView_gender_fragment_tracksAlbum);
        textView_artistName = view.findViewById(R.id.textView_nameArtist_fragment_albumsTracks);
        image_albumCover = view.findViewById(R.id.image_fragment_albumTracks);




        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        albumTracksViewModel = ViewModelProviders.of(this).get(AlbumTracksViewModel.class);
        AlbumTracksFragmentArgs args = AlbumTracksFragmentArgs.fromBundle(getArguments());
        album = args.getAlbum();
        artist = args.getArtist();
        textView_artistName.setText( artist.getName());
        textView_albumTitle.setText(album.getTitle());
        Glide.with(this).load(album.getCover()).into(image_albumCover);



    }

}