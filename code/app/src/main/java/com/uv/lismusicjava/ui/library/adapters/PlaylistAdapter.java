package com.uv.lismusicjava.ui.library.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Playlist;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {
    ArrayList<Playlist> listPlaylist;
    final private ListItemClick onclickListener;
    private Context context;

    public PlaylistAdapter(ArrayList<Playlist> listPlaylist, ListItemClick listItemClick, Context context) {
        this.listPlaylist = listPlaylist;
        this.onclickListener = listItemClick;
        this.context = context;
    }

    public  interface  ListItemClick{
        void onListItemClick(int clickedItem);
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item_layout, null,false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        holder.namePlaylist.setText(listPlaylist.get(position).getTitle());
        holder.nameOwner.setText((listPlaylist.get(position).getOwner()));
        Glide.with(context).load(listPlaylist.get(position).getCover()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listPlaylist.size();
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView namePlaylist, nameOwner;
        ImageView image;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            namePlaylist = (TextView) itemView.findViewById(R.id.textView_namePlaylist_playlist_item);
            nameOwner = (TextView) itemView.findViewById(R.id.textView_nameOwner_playlist_item);
            image = (ImageView)  itemView.findViewById(R.id.imageView_playlist_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedItem = getAdapterPosition();
            onclickListener.onListItemClick((clickedItem));
        }
    }
}
