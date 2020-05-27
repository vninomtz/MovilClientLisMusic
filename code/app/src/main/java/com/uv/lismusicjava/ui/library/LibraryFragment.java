package com.uv.lismusicjava.ui.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.uv.lismusicjava.R;

public class LibraryFragment extends Fragment {

    private LibraryViewModel libraryViewModel;
    LibraryPagerAdapter libraryPagerAdapter;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*libraryViewModel =
                ViewModelProviders.of(this).get(LibraryViewModel.class);*/

        //final TextView textView = root.findViewById(R.id.text_notifications);
        /*libraryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        View root = inflater.inflate(R.layout.fragment_library, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        libraryPagerAdapter = new LibraryPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pagerLibrary);
        viewPager.setAdapter(libraryPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layoutLibrary);
        tabLayout.setupWithViewPager(viewPager);
    }

}
