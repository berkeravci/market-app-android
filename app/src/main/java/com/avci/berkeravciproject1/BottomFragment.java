package com.avci.berkeravciproject1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class BottomFragment extends Fragment {

    ImageView frgBottomImg;
    int[] imgIds = new int[]{R.drawable.breadd, R.drawable.water, R.drawable.beer,R.drawable.rice,R.drawable.chocolate};

    public BottomFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frgBottomImg = view.findViewById(R.id.frgBottomImg);

        int pos = getArguments().getInt("position");
        frgBottomImg.setImageResource(imgIds[pos]);
    }


    void changeProductImage(int position){
        frgBottomImg.setImageResource(imgIds[position]);
    }

}