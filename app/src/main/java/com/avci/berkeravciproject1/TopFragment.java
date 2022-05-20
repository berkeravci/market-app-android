package com.avci.berkeravciproject1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopFragment extends Fragment {

    Spinner frgTopSpinnerProduct;
    boolean isDefaultSelection=true;



    TopFragmentInterface topFragmentInterfaceListener;

    interface  TopFragmentInterface{
        public void changeImage(int position);
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof TopFragmentInterface )
            topFragmentInterfaceListener = (TopFragmentInterface) context;

    }


    public TopFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false) ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frgTopSpinnerProduct = view.findViewById(R.id.frgTopSpinnerProduct);


        frgTopSpinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isDefaultSelection)
                    isDefaultSelection = false;
                else{
                    String str = frgTopSpinnerProduct.getSelectedItem().toString();



                    topFragmentInterfaceListener.changeImage(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}