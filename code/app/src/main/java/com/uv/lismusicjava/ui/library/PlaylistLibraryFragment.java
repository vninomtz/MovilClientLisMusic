package com.uv.lismusicjava.ui.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Playlist;
import com.uv.lismusicjava.jsonmanagement.GsonRequest;
import com.uv.lismusicjava.jsonmanagement.SingletonRequestQueue;
import com.uv.lismusicjava.models.Track;
import com.uv.lismusicjava.ui.library.adapters.PlaylistAdapter;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistLibraryFragment extends Fragment  implements PlaylistAdapter.ListItemClick{

    private PlaylistLibraryViewModel mViewModel;
    private RecyclerView recyclerPlaylist;
    ArrayList<Playlist> listPlaylist;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        loadPlaylist();

        Log.i("Montandoadapter","Montado");
        listPlaylist = new ArrayList<>();

        View viewFragment = inflater.inflate(R.layout.fragment_playlist_library, container, false);
        recyclerPlaylist = viewFragment.findViewById(R.id.recyclerView_playlist_library);

        return viewFragment;
    }

    private void loadPlaylist() {
        System.out.println("Cargando api");
        String ip = getString(R.string.ip);
        String idAccount = SingletonAccount.getSingletonAccount().getIdAccount();
        String url = "http://"+ip+":6000/account/"+idAccount+"/playlist";
        Log.i("Creando request", url);

        String token = SingletonAccount.getSingletonAccount().getAccesToken();
        Log.i("Token", token);
        Map mapHeaders = new HashMap <String, String>(); //agregar headers necesarios
        mapHeaders.put("Content-Type", "application/json; charset=utf-8");
        GsonRequest<Playlist[]> requestPlaylist = new GsonRequest<Playlist[]>(
                url,
                Playlist[].class,
                mapHeaders,
                myRequestSuccessListener(), //Success Listener
                myRequestErrorListener() //Error Listener
        );
        SingletonRequestQueue.getInstance(getContext()).addToRequestQueue(requestPlaylist);
    }

    private Response.Listener<Playlist[]> myRequestSuccessListener() {
        return new Response.Listener<Playlist[]>() {
            @Override
            public void onResponse(Playlist[] response) {
                handlerGsonResponse(response);
            }

        };
    }

    private void handlerGsonResponse(Playlist[] playlist){

        recyclerPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPlaylist.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        for (int i  = 0; i < playlist.length; i++) {
            listPlaylist.add(playlist[i]);
            System.out.println(playlist[i].getTitle());

        }
        PlaylistAdapter adapter = new PlaylistAdapter(listPlaylist, this,this.getContext());
        recyclerPlaylist.setAdapter(adapter);
    }

    private Response.ErrorListener myRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.toString());
            }

        };
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {




    }

    @Override
    public void onListItemClick(int clickedItem) {
        String message = "Playlist clicked: " + listPlaylist.get(clickedItem).getTitle();
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }
}
