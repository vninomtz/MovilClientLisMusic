package com.uv.lismusicjava.ui.library.playlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.ui.library.LibraryFragmentDirections;
import com.uv.lismusicjava.ui.library.adapters.PlaylistAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlaylistLibraryFragment extends Fragment implements PlaylistAdapter.ListItemClick {

    RecyclerView recyclerPlaylist;
    ArrayList<Playlist> listPlaylist = new ArrayList<>();
    PlaylistLibraryViewModel playlistViewModel;
    PlaylistAdapter playlistAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_playlist_library, container, false);
        recyclerPlaylist = viewFragment.findViewById(R.id.recyclerView_playlist_library);

        playlistViewModel = ViewModelProviders.of(this).get(PlaylistLibraryViewModel.class);
        playlistViewModel.init();
        playlistViewModel.getPlaylistRepository().observe(getViewLifecycleOwner(), playlistResponse -> {
            List<Playlist> playlists = playlistResponse;
            if(playlists != null){
                listPlaylist.clear();
                listPlaylist.addAll(playlists);
                playlistAdapter.notifyDataSetChanged();
            }


        });
        setupRecyclerView();
        return viewFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupRecyclerView() {
        if (playlistAdapter == null) {
            playlistAdapter = new PlaylistAdapter(listPlaylist, this, this.getContext());
            recyclerPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerPlaylist.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            recyclerPlaylist.setAdapter(playlistAdapter);
        } else {
            playlistAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(int clickedItem, View itemView) {
        Playlist playlist = listPlaylist.get(clickedItem);
        LibraryFragmentDirections.ActionNavigationLibraryToTrackFragment action = LibraryFragmentDirections.actionNavigationLibraryToTrackFragment(playlist);
        NavHostFragment.findNavController(this).navigate(action);
    }
}
