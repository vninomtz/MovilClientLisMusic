package com.uv.lismusicjava.ui.library;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Album;
import com.uv.lismusicjava.jsonmanagement.GsonRequest;
import com.uv.lismusicjava.jsonmanagement.SingletonRequestQueue;
import com.uv.lismusicjava.ui.library.adapters.AlbumAdapter;
import com.uv.lismusicjava.utils.SingletonAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AlbumLibraryFragment extends Fragment implements AlbumAdapter.ListItemClick{
    private RecyclerView recyclerViewAlbums;
    private ArrayList<Album> arrayListAlbum;
    public AlbumLibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadAlbums();
        arrayListAlbum = new ArrayList<>();
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_album_library,container,false);
        recyclerViewAlbums = viewFragment.findViewById(R.id.recyclerView_albums_likes);

        return viewFragment;
    }
    private void loadAlbums(){
        String ip = getString(R.string.ip);
        String accountId = SingletonAccount.getSingletonAccount().getIdAccount();
        String token = SingletonAccount.getSingletonAccount().getAccesToken();
        Log.i("Token:", token);
        String url = "http://"+ip+":6000/account/"+accountId+"/albumsLike";
        Map<String, String> mapHeaders = new HashMap<String, String>(); //agregar headers necesarios
        mapHeaders.put("Content-Type", "application/json; charset=utf-8");
        mapHeaders.put("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiYjQ4NWY0MGYtNGYyYi00M2I2LTljNmEtNjA0ODc1ZjMxODMyIiwiZXhwIjoxNTk0MTkzMjkxfQ.EUbz1WoJbjDZvPVfOkwH_ktbLzFDKCbvILUVqfThJ-o");
        GsonRequest<Album[]> requestAlbum = new GsonRequest<Album[]>(
                url,
                Album[].class,
                mapHeaders,
                myRequestSuccessListener(), //Success Listener
                myRequestErrorListener() //Error Listener
        );
        SingletonRequestQueue.getInstance(getContext()).addToRequestQueue(requestAlbum);
    }
    private Response.Listener<Album[]> myRequestSuccessListener() {
        return new Response.Listener<Album[]>() {
            @Override
            public void onResponse(Album[] response) {
                handlerGsonResponse(response);
            }

        };
    }
    private void handlerGsonResponse(Album[] albums){

        recyclerViewAlbums.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAlbums.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        for (int i  = 0; i < albums.length; i++) {
            arrayListAlbum.add(albums[i]);
            System.out.println(albums[i].getTitle());

        }
        AlbumAdapter adapter = new AlbumAdapter(arrayListAlbum, this,this.getContext());
        recyclerViewAlbums.setAdapter(adapter);
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
    public void onListItemClick(int clickedItem) {
        String message = "Album clicked: " + arrayListAlbum.get(clickedItem).getTitle();
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }
}
