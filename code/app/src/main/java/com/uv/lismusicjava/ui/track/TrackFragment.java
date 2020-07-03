package com.uv.lismusicjava.ui.track;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.track.Track;
import com.uv.lismusicjava.ui.track.adapter.TrackAdapter;

import java.util.ArrayList;

public class TrackFragment extends Fragment implements TrackAdapter.OnItemClickListener {

    private TrackViewModel mViewModel;
    private ArrayList<Track> listTracks;
    private RecyclerView recyclerTracks;
    private TrackAdapter trackAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_fragment, container, false);
        recyclerTracks = view.findViewById(R.id.recyclerView_tracks);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);
        listTracks = new ArrayList<>();
        listTracks.add(new Track("12345","Canción1ffffffffffffffffffffffffffffffffffffffffffffffff",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción2",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción3",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción4",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción5",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción1",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción2",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción3",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción4",122,"file",true,"sincover","Artista"));
        listTracks.add(new Track("12345","Canción5",122,"file",true,"sincover","Artista"));
        setupRecyclerView();
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
        String message = "Track clicked: " + listTracks.get(position).getTitle();
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOptionsClick(int position) {
        String message = "Options of track: " + listTracks.get(position).getTitle();
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }
}