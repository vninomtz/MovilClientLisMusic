package com.uv.lismusicjava.ui.library.Album;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uv.lismusicjava.Albums.Album;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.ui.library.LibraryFragmentDirections;
import com.uv.lismusicjava.ui.library.adapters.AlbumAdapter;

import java.util.ArrayList;
import java.util.List;


public class AlbumLibraryFragment extends Fragment implements AlbumAdapter.ListItemClick{
    RecyclerView recyclerViewAlbums;
    ArrayList<com.uv.lismusicjava.Albums.Album> arrayListAlbum = new ArrayList<>();
    AlbumLibraryViewModel albumViewModel;
    AlbumAdapter albumAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_album_library,container,false);
        recyclerViewAlbums = viewFragment.findViewById(R.id.recyclerView_albums_likes);

        albumViewModel = ViewModelProviders.of(this).get(AlbumLibraryViewModel.class);
        albumViewModel.init();
        albumViewModel.getAlbumsRepository().observe(getViewLifecycleOwner(), albumResponse ->{
            List<com.uv.lismusicjava.Albums.Album> albumsLikes = albumResponse;
            if(albumsLikes !=null){
                arrayListAlbum.clear();
                arrayListAlbum.addAll(albumsLikes);
                arrayListAlbum.size();
                setupRecyclerView();
            }
        });
        return viewFragment;
    }

    private void setupRecyclerView() {
        if(albumAdapter == null){
            albumAdapter = new AlbumAdapter(arrayListAlbum, this, this.getContext());
            recyclerViewAlbums.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewAlbums.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
            recyclerViewAlbums.setAdapter(albumAdapter);
        }else{
            albumAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onListItemClick(int clickedItem) {
        Album albumSelected = arrayListAlbum.get(clickedItem);
        LibraryFragmentDirections.ActionNavigationLibraryToAlbumTracksFragment action = LibraryFragmentDirections.actionNavigationLibraryToAlbumTracksFragment(null, albumSelected);
        NavHostFragment.findNavController(this).navigate(action);
    }
}
