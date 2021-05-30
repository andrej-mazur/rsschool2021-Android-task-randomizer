package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements FirstFragmentStarter, SecondFragmentStarter {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFirstFragment(0);
    }

    @Override
    public void startFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, firstFragment)
                .commit();
    }

    @Override
    public void startSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, secondFragment)
                .commit();
    }
}