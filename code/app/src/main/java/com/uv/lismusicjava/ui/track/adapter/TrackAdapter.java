package com.uv.lismusicjava.ui.track.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.track.Track;

import java.util.ArrayList;


public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    ArrayList<Track> listTracks;
    final private OnItemClickListener onItemClickListener;
    private Context context;

    public TrackAdapter(ArrayList<Track> listTracks, OnItemClickListener onItemClickListener, Context context) {
        this.listTracks = listTracks;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    public interface  OnItemClickListener{
        void onItemClick(int position);
        void onOptionsClick(int position);
    }
    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item_layout, null,false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        holder.title.setText(listTracks.get(position).getTitle());
        holder.artist.setText((listTracks.get(position).getArtistName()));
        //Glide.with(context).load(listTracks.get(position).getCover()).into(holder.cover);
    }

    @Override
    public int getItemCount() {
        return listTracks.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        TextView title, artist;
        ImageView cover, options;
        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_track);
            artist = itemView.findViewById(R.id.name_artist);
            cover = itemView.findViewById(R.id.image_item_track);
            options = itemView.findViewById(R.id.image_options);
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
