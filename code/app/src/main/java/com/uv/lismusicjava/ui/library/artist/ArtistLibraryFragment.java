package com.uv.lismusicjava.ui.library.artist;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.artist.Artist;
import com.uv.lismusicjava.ui.library.adapters.ArtistsLikeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistLibraryFragment extends Fragment implements ArtistsLikeAdapter.ListItemClick {
    private RecyclerView recyclerArtistsView;
    ArrayList<Artist> listArtists = new ArrayList<>();;
    ArtistLibraryViewModel artistLibraryViewModel;
    ArtistsLikeAdapter artistsLikeAdapter;


    public ArtistLibraryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewFragment = inflater.inflate(R.layout.fragment_artist_library, container, false);
        recyclerArtistsView = viewFragment.findViewById(R.id.recyclerView_artist_library);

        artistLibraryViewModel = ViewModelProviders.of(this).get(ArtistLibraryViewModel.class);
        artistLibraryViewModel.init();

        artistLibraryViewModel.getArtistsRepository().observe(getViewLifecycleOwner(), artistReponse -> {
            List<Artist> artists = artistReponse;
            if(artists != null){
                listArtists.clear();
                listArtists.addAll(artists);
                setupRecyclerView();
            }
        });

        return viewFragment;
    }

    private void setupRecyclerView(){
        if(artistsLikeAdapter == null){
            artistsLikeAdapter = new ArtistsLikeAdapter(listArtists, this, this.getContext());
            recyclerArtistsView.setAdapter(artistsLikeAdapter);
            recyclerArtistsView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerArtistsView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }else{
            artistsLikeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(int clickedItem) {
        String message = "Artist clicked: " + listArtists.get(clickedItem).getName();
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }
}

