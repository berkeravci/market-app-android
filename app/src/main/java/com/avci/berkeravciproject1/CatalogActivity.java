package com.avci.berkeravciproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity implements  TopFragment.TopFragmentInterface{


    TopFragment topFragment;
    BottomFragment bottomFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);



        bottomFragment = new BottomFragment();
        Bundle b = new Bundle();
        b.putInt("position",0);
        bottomFragment.setArguments(b);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dynamicFragmentLayout, bottomFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeImage(int position) {

        bottomFragment.changeProductImage(position);

    }
}