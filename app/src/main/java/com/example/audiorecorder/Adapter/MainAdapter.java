package com.example.audiorecorder.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.audiorecorder.Feature.Record.RecordScreenFragment;
import com.example.audiorecorder.Feature.Player.PlayerScreenFragment;

public class MainAdapter extends FragmentStatePagerAdapter {

    public MainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new RecordScreenFragment();
            }
            case 1: {
                return new PlayerScreenFragment();
            }
            default:
                return new RecordScreenFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
