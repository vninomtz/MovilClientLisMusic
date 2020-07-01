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
import com.uv.lismusicjava.domain.Artist;

import java.util.ArrayList;


public class ArtistsLikeAdapter extends RecyclerView.Adapter<ArtistsLikeAdapter.ArtistsLikeViewHolder>  {
    private ArrayList<Artist> listArtists;
    final private ListItemClick onclickListener;
    private Context context;

    public ArtistsLikeAdapter(ArrayList<Artist> listArtists, ListItemClick listItemClick, Context context) {
        this.listArtists = listArtists;
        this.onclickListener = listItemClick;
        this.context = context;
    }


    public  interface  ListItemClick{
        void onListItemClick(int clickedItem);
    }

    @NonNull
    @Override
    public ArtistsLikeAdapter.ArtistsLikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item_layout, null,false);
        return new ArtistsLikeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArtistsLikeAdapter.ArtistsLikeViewHolder holder, int position) {
        holder.nameArtist.setText(listArtists.get(position).getName());
        Glide.with(context).load(listArtists.get(position).getCover()).into(holder.imageArtist);

    }

    @Override
    public int getItemCount() {
        return listArtists.size();
    }

    public class ArtistsLikeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameArtist;
        ImageView imageArtist;
        public ArtistsLikeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameArtist = (TextView) itemView.findViewById(R.id.textView_name_artist_item);
            imageArtist = (ImageView) itemView.findViewById(R.id.imageView_artist_item);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clickedItem = getAdapterPosition();
            onclickListener.onListItemClick((clickedItem));
        }
    }
}
