package com.uv.lismusicjava.ui.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uv.lismusicjava.R;

public class PlaylistLibraryFragment extends Fragment {

    private PlaylistLibraryViewModel mViewModel;
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*Bundle args = getArguments();
        TextView textView = view.findViewById(R.id.text1);
        textView.setText(Integer.toString(args.getInt(ARG_OBJECT)));*/

    }

}
