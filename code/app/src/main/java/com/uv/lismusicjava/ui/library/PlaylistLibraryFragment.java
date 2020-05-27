package com.uv.lismusicjava.ui.library;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Playlist;
import com.uv.lismusicjava.ui.library.adapters.PlaylistAdapter;

import java.util.ArrayList;

public class PlaylistLibraryFragment extends Fragment  implements PlaylistAdapter.ListItemClick{

    private PlaylistLibraryViewModel mViewModel;
    private RecyclerView recyclerPlaylist;
    ArrayList<Playlist> listPlaylist;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_playlist_library, container, false);
        listPlaylist = new ArrayList<>();
        recyclerPlaylist = viewFragment.findViewById(R.id.recyclerView_playlist_library);
        recyclerPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPlaylist.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        loadPlaylist();
        PlaylistAdapter adapter = new PlaylistAdapter(listPlaylist, this);
        recyclerPlaylist.setAdapter(adapter);
        return viewFragment;



    }

    private void loadPlaylist() {
        listPlaylist.add(new Playlist(1, "Canciones que me gustan", "Victor Niño","url"));
        listPlaylist.add(new Playlist(2, "Canciones que me gustan2", "Victor Niño","url"));
        listPlaylist.add(new Playlist(3, "Canciones que me gustan3", "Victor Niño","url"));
        listPlaylist.add(new Playlist(4, "Canciones que me gustan4", "Victor Niño","url"));
        listPlaylist.add(new Playlist(1, "Canciones que me gustan", "Victor Niño","url"));
        listPlaylist.add(new Playlist(2, "Canciones que me gustan2", "Victor Niño","url"));
        listPlaylist.add(new Playlist(3, "Canciones que me gustan3", "Victor Niño","url"));
        listPlaylist.add(new Playlist(4, "Canciones que me gustan4", "Victor Niño","url"));
        listPlaylist.add(new Playlist(1, "Canciones que me gustan", "Victor Niño","url"));
        listPlaylist.add(new Playlist(2, "Canciones que me gustan2", "Victor Niño","url"));
        listPlaylist.add(new Playlist(3, "Canciones que me gustan3", "Victor Niño","url"));
        listPlaylist.add(new Playlist(4, "Canciones que me gustan4", "Victor Niño","url"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*Bundle args = getArguments();
        TextView textView = view.findViewById(R.id.text1);
        textView.setText(Integer.toString(args.getInt(ARG_OBJECT)));*/


    }

    @Override
    public void onListItemClick(int clickedItem) {
        String message = "Playlist clicked: " + listPlaylist.get(clickedItem).getName();
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }
}
