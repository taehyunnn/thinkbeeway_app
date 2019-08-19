package com.example.thinkbeeway.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thinkbeeway.R;

public class FindPathText extends Fragment {

    private TextView startText;
    private TextView endText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_find_path_text, container, false);

        startText = rootView.findViewById(R.id.startText);
        endText = rootView.findViewById(R.id.endText);

        return rootView;
    }


}
