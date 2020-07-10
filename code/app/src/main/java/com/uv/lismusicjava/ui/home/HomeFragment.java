package com.uv.lismusicjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Gender;
import com.uv.lismusicjava.playlist.Playlist;
import com.uv.lismusicjava.ui.home.Apadter.GenderAdapter;
import com.uv.lismusicjava.ui.home.Apadter.HomeAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ArrayList<Playlist> listRecommendatios;
    private RecyclerView recyclerRecommendatios, recyclerGender;
    private ArrayList<Gender> genderList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        genderList = new ArrayList<>();
        listRecommendatios = new ArrayList<>();

        recyclerRecommendatios = view.findViewById(R.id.recyclerViewRecommendations);
        recyclerRecommendatios.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        loadRecommendations();

        recyclerGender = view.findViewById(R.id.recyclerViewGenders);
       // recyclerGender.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerGender.setLayoutManager(new GridLayoutManager(getContext(),2));
        loadGenders();

        HomeAdapter adapter = new HomeAdapter(listRecommendatios);
        recyclerRecommendatios.setAdapter(adapter);

        GenderAdapter genderAdapter = new GenderAdapter(genderList);
        recyclerGender.setAdapter(genderAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
    public void loadRecommendations(){
        listRecommendatios.add(new Playlist("Los más populares de LisMusic", "12"));
        listRecommendatios.add(new Playlist("LisMusic", "12"));
        listRecommendatios.add(new Playlist("Top Global", "12"));
        listRecommendatios.add(new Playlist("Top Mexico", "12"));
        listRecommendatios.add(new Playlist("Top Argentina", "12"));
        listRecommendatios.add(new Playlist("Top Latin", "12"));
        listRecommendatios.add(new Playlist("Top Old", "12"));
        listRecommendatios.add(new Playlist("Top Romantic", "12"));
    }

    private void loadGenders(){
        genderList.add(new Gender("Hip-hop", R.drawable.hiphop));
        genderList.add(new Gender("Pop", R.drawable.pop));
        genderList.add(new Gender("Romantic", R.drawable.romantic));
        genderList.add(new Gender("Old", R.drawable.old));
        genderList.add(new Gender("Mexican regional", R.drawable.mexican));
        genderList.add(new Gender("Rock", R.drawable.rock));
        genderList.add(new Gender("Latin", R.drawable.latin));
    }
}
