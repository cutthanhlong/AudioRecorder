package com.example.audiorecorder.Feature.Splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

import com.example.audiorecorder.Base.BaseActivity;
import com.example.audiorecorder.Feature.Intro.IntroScreenActivity;
import com.example.audiorecorder.Feature.Language.LanguageStartActivity;
import com.example.audiorecorder.R;
import com.example.audiorecorder.Utils.SharePrefUtils;

public class SplashScreenActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    public ActivitySplashBinding getBinding() {
        return ActivitySplashBinding.inflate(LayoutInflater.from(this));
    }

    @Override
    public void getData() {
        SharePrefUtils.increaseCountOpenApp(SplashScreenActivity.this);
    }

    @Override
    public void initView() {
        setStatusBarGradiant(this, R.drawable.bg_app);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                showButton();
            }
        }, 4500);
    }

    @Override
    public void bindView() {
        binding.tvStart.setOnClickListener(view -> {
            startAct();
        });
    }

    public void showButton() {
        binding.tvStart.setVisibility(View.VISIBLE);
        binding.tvLoad.setVisibility(View.GONE);
        binding.pbLoading.setVisibility(View.GONE);
    }

    public void startAct() {
        if (SharePrefUtils.getCountOpenFirstHelp(this) == 0) {
            startActivity(new Intent(this, LanguageStartActivity.class));
        } else {
            startActivity(new Intent(this, IntroScreenActivity.class));
        }
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
