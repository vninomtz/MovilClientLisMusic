package com.uv.lismusicjava.ui.library.artistalbums;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uv.lismusicjava.Albums.Album;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.artist.Artist;
import com.uv.lismusicjava.ui.library.LibraryFragmentDirections;
import com.uv.lismusicjava.ui.library.adapters.AlbumAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArtistAlbumFragment extends Fragment implements AlbumAdapter.ListItemClick {

    private ArtistAlbumViewModel artistAlbumViewModel;
    private RecyclerView recyclerView;
    private ArrayList<Album> listAlbums = new ArrayList<>();
    private ImageView artistImage;
    private TextView artistName;
    private TextView artistDescription;
    private AlbumAdapter albumAdapter;
    private Artist artist;

    public static ArtistAlbumFragment newInstance() {
        return new ArtistAlbumFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.artist_album_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_artistAlbums);
        artistImage = view.findViewById(R.id.image_fragment_artistAlbums);
        artistName = view.findViewById(R.id.textView_fragment_artistName);
        artistDescription = view.findViewById(R.id.textView_artist_description);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        artistAlbumViewModel = ViewModelProviders.of(this).get(ArtistAlbumViewModel.class);
        ArtistAlbumFragmentArgs artistArg = ArtistAlbumFragmentArgs.fromBundle(getArguments());
        artist = artistArg.getArtist();
        System.out.println("Artista: " + artist.getName());
        artistName.setText(artist.getName());
        Glide.with(this).load(artist.getCover()).into(artistImage);
        artistDescription.setText(artist.getDescription());
        artistAlbumViewModel.init(artist.getIdArtist());
        artistAlbumViewModel.getAlbumsOfArtist().observe(getViewLifecycleOwner(), response -> {
            List<Album> albums = response;
            listAlbums.addAll(albums);
            albumAdapter.notifyDataSetChanged();


        });
        setupRecyclerView();



    }

    private void setupRecyclerView() {
        if(albumAdapter == null){
            albumAdapter = new AlbumAdapter(listAlbums, this, this.getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(albumAdapter);
        }else{
            albumAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(int clickedItem) {
        Album albumSelected = listAlbums.get(clickedItem);
        ArtistAlbumFragmentDirections.ActionArtistAlbumFragmentToAlbumTracksFragment action = ArtistAlbumFragmentDirections.actionArtistAlbumFragmentToAlbumTracksFragment(artist,albumSelected);
        NavHostFragment.findNavController(this).navigate(action);
    }
}