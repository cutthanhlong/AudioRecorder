package com.example.audiorecorder.Dialog;

import android.content.Context;

import com.example.audiorecorder.Base.BaseDialog;
import com.example.audiorecorder.Interface.IClickDialogExit;
import com.example.audiorecorder.databinding.DialogExitAppBinding;

public class DialogExitApp extends BaseDialog<DialogExitAppBinding> {
    IClickDialogExit iBaseListener;
    Context context;

    public DialogExitApp(Context context, Boolean cancelAble, IClickDialogExit iBaseListener) {
        super(context, cancelAble);
        this.context = context;
        this.iBaseListener = iBaseListener;
    }


    @Override
    protected DialogExitAppBinding setBinding() {
        return DialogExitAppBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindView() {
        binding.btnCancelQuitApp.setOnClickListener(view -> iBaseListener.onCancel());

        binding.btnQuitApp.setOnClickListener(view -> iBaseListener.onExit());
    }
}
