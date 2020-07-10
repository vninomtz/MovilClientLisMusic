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
import com.uv.lismusicjava.artist.Artist;
import com.uv.lismusicjava.ui.library.adapters.ArtistsLikeAdapter;

import java.util.ArrayList;

public class SearchArtistFragment extends Fragment implements ArtistsLikeAdapter.ListItemClick{
    RecyclerView recyclerArtistsView;
    ArrayList<Artist> listArtists;
    ArtistsLikeAdapter artistsLikeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_artist, container, false);
        recyclerArtistsView = view.findViewById(R.id.recyclerView_search_artist);
        listArtists = new ArrayList<>();
        listArtists.add(new Artist("1234","Artist 1","http://10.0.2.2:6000/media/artists/defaultArtistCover.jpeg",null,null));
        listArtists.add(new Artist("1234","Artist 2","http://10.0.2.2:6000/media/artists/defaultArtistCover.jpeg",null,null));
        listArtists.add(new Artist("1234","Artist 3","http://10.0.2.2:6000/media/artists/defaultArtistCover.jpeg",null,null));
        listArtists.add(new Artist("1234","Artist 4","http://10.0.2.2:6000/media/artists/defaultArtistCover.jpeg",null,null));
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        if(artistsLikeAdapter == null){
            artistsLikeAdapter = new ArtistsLikeAdapter(listArtists, this, this.getContext());
            recyclerArtistsView.setAdapter(artistsLikeAdapter);
            recyclerArtistsView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerArtistsView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }else{
            artistsLikeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(int clickedItem) {

    }
}