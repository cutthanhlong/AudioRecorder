package com.example.audiorecorder.Feature.Player;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.audiorecorder.Base.BaseFragment;
import com.example.audiorecorder.databinding.FragmentPlayerBinding;

public class PlayerScreenFragment extends BaseFragment<FragmentPlayerBinding> {

    String TAG = "SettingScreenFragment";

    @Override
    public FragmentPlayerBinding setBinding(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentPlayerBinding.inflate(inflater, container, false);
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {

    }


}