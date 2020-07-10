package com.uv.lismusicjava.ui.search.searchOptions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uv.lismusicjava.Albums.Album;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.ui.library.adapters.AlbumAdapter;

import java.util.ArrayList;

public class SearchAlbumFragment extends Fragment implements AlbumAdapter.ListItemClick{
    RecyclerView recyclerViewAlbums;
    ArrayList<Album> arrayListAlbum;
    AlbumAdapter albumAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_album, container, false);
        recyclerViewAlbums = view.findViewById(R.id.recyclerView_search_albums);
        arrayListAlbum  = new ArrayList<>();
        arrayListAlbum.add(new Album("1234","Album 1","http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg",
                null,null,null,0,null));
        arrayListAlbum.add(new Album("1234","Album 2","http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg",
                null,null,null,0,null));
        arrayListAlbum.add(new Album("1234","Album 3","http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg",
                null,null,null,0,null));
        arrayListAlbum.add(new Album("1234","Album 4","http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg",
                null,null,null,0,null));

        setupRecyclerView();
        return view;
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

    }
}