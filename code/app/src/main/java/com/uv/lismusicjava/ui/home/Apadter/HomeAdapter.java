package com.uv.lismusicjava.ui.home.Apadter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Gender;
import com.uv.lismusicjava.playlist.Playlist;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolderHome> {
    ArrayList<Playlist> playlistRecommedations;
    ArrayList<Gender> genderPlaylist;


    public HomeAdapter(ArrayList<Playlist> playlistRecommedations){
        this.playlistRecommedations = playlistRecommedations;
    }
    @Override
    public ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommendatios_layout,null,false);
        return new ViewHolderHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHome holder, int position) {
        holder.playlistName.setText(playlistRecommedations.get(position).getTitle());
        holder.playlistCover.setImageResource(R.drawable.playlist2);
    }

    @Override
    public int getItemCount() {
        return playlistRecommedations.size();
    }

    public class ViewHolderHome extends RecyclerView.ViewHolder {
        ImageView playlistCover;
        TextView playlistName;
        public ViewHolderHome(@NonNull View itemView) {
            super(itemView);
            playlistCover = (ImageView) itemView.findViewById(R.id.imageViewPlaylist);
            playlistName = (TextView) itemView.findViewById(R.id.playlist_name);
        }
    }
}
