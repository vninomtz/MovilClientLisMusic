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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.track.Track;
import com.uv.lismusicjava.ui.track.adapter.TrackAdapter;

import java.util.ArrayList;
import java.util.List;

public class TrackFragment extends Fragment implements TrackAdapter.OnItemClickListener {

    private TrackViewModel mViewModel;
    private ArrayList<Track> listTracks = new ArrayList<>();
    private RecyclerView recyclerTracks;
    private TrackAdapter trackAdapter;
    private ImageView cover;
    private TextView title, artist;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_fragment, container, false);
        recyclerTracks = view.findViewById(R.id.recyclerView_tracks);
        cover = view.findViewById(R.id.image_fragment_tracks);
        title = view.findViewById(R.id.textView_Title);
        artist = view.findViewById((R.id.textView_subtitle));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);
        TrackFragmentArgs args = TrackFragmentArgs.fromBundle(getArguments());
        Playlist playlist = args.getPlaylist();
        if(playlist != null){
            title.setText(playlist.getTitle());
            artist.setText(playlist.getOwner());
            Glide.with(this).load(playlist.getCover()).into(cover);
            mViewModel.init();
            mViewModel.getTracksPlaylist(playlist.getIdPlaylist()).observe(getViewLifecycleOwner(), response ->{
                List<Track> trackList = response;
                if(trackList != null){
                    listTracks.clear();
                    listTracks.addAll(trackList);
                    setupRecyclerView();
                }
            });
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("Se inicia la actividad");
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