package com.uv.lismusicjava.ui.library.adapters;

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
import com.uv.lismusicjava.domain.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumlistViewHolder> {
    final private ListItemClick onclickListener;
    ArrayList<Album> albumsList;
    private Context context;

    public AlbumAdapter(ArrayList<Album> albumsList, ListItemClick listItemClick, Context context) {
        this.albumsList = albumsList;
        this.onclickListener = listItemClick;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumAdapter.AlbumlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item_layout,null,false);
        return new AlbumlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.AlbumlistViewHolder holder, int position) {
        holder.albumTitle.setText(albumsList.get(position).getTitle());
        holder.recordCompanyName.setText(albumsList.get(position).getRecordCompany());
        Glide.with(context).load(albumsList.get(position).getCover()).into(holder.cover);
    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public  interface  ListItemClick{
        void onListItemClick(int clickedItem);
    }

    public class AlbumlistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView albumTitle, recordCompanyName;
        ImageView cover;
        public AlbumlistViewHolder(@NonNull View itemView) {
            super(itemView);
            albumTitle = itemView.findViewById(R.id.textView_albumTitle_item);
            recordCompanyName = itemView.findViewById(R.id.textView_recordCompany_item);
            cover = itemView.findViewById(R.id.imageView_album_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedItem = getAdapterPosition();
            onclickListener.onListItemClick(clickedItem);
        }
    }
}
