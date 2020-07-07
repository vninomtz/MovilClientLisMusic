package com.uv.lismusicjava.ui.library.albumtracks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.track.Track;
import com.uv.lismusicjava.ui.track.adapter.TrackAdapter;


import java.util.ArrayList;

public class TracksAlbumAdapter extends RecyclerView.Adapter<TracksAlbumAdapter.TracksAlbumViewHolder> {
    ArrayList<Track> listTracks;
    final private OnItemClickListener onItemClickListener;


    public TracksAlbumAdapter(ArrayList<Track> listTracks, OnItemClickListener onItemClickListener){
        this.listTracks = listTracks;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TracksAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_tracks, null,false);
        return new TracksAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TracksAlbumViewHolder holder, int position) {
        int numberTrack = position + 1;
        holder.titleTrack.setText(numberTrack + "   "+ listTracks.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return listTracks.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onOptionsClick(int position);
    }

    public class TracksAlbumViewHolder extends RecyclerView.ViewHolder{
        TextView titleTrack;
        ImageView options;

        public TracksAlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTrack = itemView.findViewById(R.id.textView_nameTrack_item_album_tracks);
            options = itemView.findViewById(R.id.imageView_options);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(position);
                }
            });
            options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onOptionsClick(position);
                    }
                }
            });
        }
    }
}
