package com.uv.lismusicjava.ui.home.Apadter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Gender;

import java.util.ArrayList;

public class GenderAdapter extends RecyclerView.Adapter<GenderAdapter.ViewHolderHome> {
    ArrayList<Gender> genderPlaylist;


    public GenderAdapter(ArrayList<Gender> genderPlaylist){
        this.genderPlaylist = genderPlaylist;
    }
    @Override
    public GenderAdapter.ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gender_layout,null,false);
        return new GenderAdapter.ViewHolderHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenderAdapter.ViewHolderHome holder, int position) {
        holder.playlistName.setText(genderPlaylist.get(position).getGenderName());
        holder.playlistCover.setImageResource(genderPlaylist.get(position).getCoverGender());
    }

    @Override
    public int getItemCount() {
        return genderPlaylist.size();
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
