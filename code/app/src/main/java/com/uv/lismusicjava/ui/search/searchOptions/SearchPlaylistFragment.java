package com.uv.lismusicjava.ui.search.searchOptions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.ui.library.adapters.PlaylistAdapter;

import java.util.ArrayList;

public class SearchPlaylistFragment extends Fragment implements PlaylistAdapter.ListItemClick{
    RecyclerView recyclerPlaylist;
    ArrayList<Playlist> listPlaylist;
    PlaylistAdapter playlistAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_playlist, container, false);
        recyclerPlaylist = view.findViewById(R.id.recyclerView_search_playlists);
        listPlaylist = new ArrayList<>();
        listPlaylist.add(new Playlist(11,"Playlist 1", null,"http://10.0.2.2:6000/media/playlists/defaultPlaylistCover.jpeg",
                null,null,0,false));
        listPlaylist.add(new Playlist(11,"Playlist 2", null,"http://10.0.2.2:6000/media/playlists/defaultPlaylistCover.jpeg",
                null,null,0,false));
        listPlaylist.add(new Playlist(11,"Playlist 3", null,"http://10.0.2.2:6000/media/playlists/defaultPlaylistCover.jpeg",
                null,null,0,false));
        listPlaylist.add(new Playlist(11,"Playlist 4", null,"http://10.0.2.2:6000/media/playlists/defaultPlaylistCover.jpeg",
                null,null,0,false));
        listPlaylist.add(new Playlist(11,"Playlist 5", null,"http://10.0.2.2:6000/media/playlists/defaultPlaylistCover.jpeg",
                null,null,0,false));
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        if (playlistAdapter == null) {
            playlistAdapter = new PlaylistAdapter(listPlaylist, this, this.getContext());
            recyclerPlaylist.setAdapter(playlistAdapter);
            recyclerPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerPlaylist.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        } else {
            playlistAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(int clickedItem, View itemView) {

    }
}