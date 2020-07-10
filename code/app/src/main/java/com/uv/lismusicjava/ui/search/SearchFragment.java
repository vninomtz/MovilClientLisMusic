package com.uv.lismusicjava.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.ui.library.playlist.PlaylistLibraryFragment;
import com.uv.lismusicjava.ui.search.searchOptions.SearchAlbumFragment;
import com.uv.lismusicjava.ui.search.searchOptions.SearchArtistFragment;
import com.uv.lismusicjava.ui.search.searchOptions.SearchPlaylistFragment;
import com.uv.lismusicjava.ui.search.searchOptions.SearchTrackFragment;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private ImageView btnSearch;
    private FrameLayout frameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        Spinner options = root.findViewById(R.id.spinner_search);
        btnSearch = root.findViewById(R.id.btn_search);
        frameLayout = root.findViewById(R.id.fragment_container_search);
        if(root.findViewById(R.id.fragment_container_search) != null){
            if(savedInstanceState == null){
                getFragmentManager().beginTransaction().add(R.id.fragment_container_search, new SearchArtistFragment()).commit();
            }
        }
        btnSearch.setOnClickListener(v -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            switch (options.getSelectedItem().toString()){
                case "Artists":
                    goSearchFragment(transaction, new SearchArtistFragment());
                    break;
                case "Albums":
                    goSearchFragment(transaction, new SearchAlbumFragment());
                    break;
                case "Tracks":
                    goSearchFragment(transaction, new SearchTrackFragment());
                    break;
                case "Playlists":
                    goSearchFragment(transaction, new SearchPlaylistFragment());
                    break;
            }

        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.searchType,
                R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        options.setAdapter(adapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
         searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
    }

    private void goSearchFragment(FragmentTransaction transaction, Fragment fragment){
        transaction.replace(R.id.fragment_container_search, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
