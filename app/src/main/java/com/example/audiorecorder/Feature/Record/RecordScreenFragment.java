package com.example.audiorecorder.Feature.Record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.audiorecorder.Base.BaseFragment;
import com.example.audiorecorder.databinding.FragmentRecordBinding;

public class RecordScreenFragment extends BaseFragment<FragmentRecordBinding> {
    String TAG = "RecordScreenFragment";

    @Override
    public FragmentRecordBinding setBinding(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return FragmentRecordBinding.inflate(inflater, container, false);
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {

    }
}
