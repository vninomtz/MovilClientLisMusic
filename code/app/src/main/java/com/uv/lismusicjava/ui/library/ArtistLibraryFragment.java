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
import com.uv.lismusicjava.domain.Artist;
import com.uv.lismusicjava.jsonmanagement.GsonRequest;
import com.uv.lismusicjava.jsonmanagement.SingletonRequestQueue;
import com.uv.lismusicjava.ui.library.adapters.ArtistsLikeAdapter;
import com.uv.lismusicjava.utils.SingletonAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistLibraryFragment extends Fragment implements ArtistsLikeAdapter.ListItemClick {
    private RecyclerView recyclerArtistsView;
    ArrayList<Artist> listArtists;


    public ArtistLibraryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loadArtists();
        // Inflate the layout for this fragment
        listArtists = new ArrayList<>();
        View viewFragment = inflater.inflate(R.layout.fragment_artist_library, container, false);
        recyclerArtistsView = viewFragment.findViewById(R.id.recyclerView_artist_library);

        return viewFragment;
    }

    private void loadArtists() {
        Log.i("Información", "cargando artistas");
        String url = "http://"+ getString(R.string.ip)+ ":6000/account/" + SingletonAccount.getSingletonAccount().getIdAccount() + "/artistsLike";
        Log.i("Creando request", url);
        Map mapHeaders = new HashMap<String, String>(); //agregar headers necesarios
        mapHeaders.put("Content-Type", "application/json; charset=utf-8");
        GsonRequest<Artist[]> requestPlaylist = new GsonRequest<Artist[]>(
                url,
                Artist[].class,
                mapHeaders,
                myRequestSuccessListener(), //Success Listener
                myRequestErrorListener() //Error Listener
        );
        SingletonRequestQueue.getInstance(getContext()).addToRequestQueue(requestPlaylist);
    }

    private Response.Listener<Artist[]> myRequestSuccessListener() {
        return new Response.Listener<Artist[]>() {
            @Override
            public void onResponse(Artist[] response) {
                handlerGsonResponse(response);
            }

        };
    }

    private void handlerGsonResponse(Artist[] artists) {
        recyclerArtistsView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerArtistsView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        for (int i  = 0; i < artists.length; i++) {
            listArtists.add(artists[i]);
            System.out.println(artists[i].getIdArtist());

        }
        ArtistsLikeAdapter adapter = new ArtistsLikeAdapter(listArtists, this,this.getContext());
        recyclerArtistsView.setAdapter(adapter);
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
        String message = "Artist clicked: " + listArtists.get(clickedItem).getName();
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }
}

