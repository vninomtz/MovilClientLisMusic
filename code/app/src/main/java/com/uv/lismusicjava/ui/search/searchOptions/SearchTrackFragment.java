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
import com.uv.lismusicjava.track.Track;
import com.uv.lismusicjava.ui.track.adapter.TrackAdapter;

import java.util.ArrayList;

public class SearchTrackFragment extends Fragment implements TrackAdapter.OnItemClickListener{
    private RecyclerView recyclerTracks;
    private TrackAdapter trackAdapter;
    private ArrayList<Track> listTracks;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_track, container, false);
        recyclerTracks = view.findViewById(R.id.recyclerView_search_tracks);
        listTracks = new ArrayList<>();
        listTracks.add(new Track("12345","Canción 1",202,"file",
                true,"http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg","name"));
        listTracks.add(new Track("12345","Canción 2",202,"file",
                true,"http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg","name"));
        listTracks.add(new Track("12345","Canción 3",202,"file",
                true,"http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg","name"));
        listTracks.add(new Track("12345","Canción 4",202,"file",
                true,"http://10.0.2.2:6000/media/albums/defaultAlbumCover.jpeg","name"));
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView(){
        if(trackAdapter == null){
            trackAdapter = new TrackAdapter(listTracks, (TrackAdapter.OnItemClickListener) this, getContext());
            recyclerTracks.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerTracks.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            recyclerTracks.setAdapter(trackAdapter);
        }else{
            trackAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onOptionsClick(int position) {

    }
}