package com.example.audiorecorder.Feature.Intro;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.example.audiorecorder.Adapter.IntroAdapter;
import com.example.audiorecorder.Base.BaseActivity;
import com.example.audiorecorder.Feature.Main.MainScreenActivity;
import com.example.audiorecorder.R;
import com.example.audiorecorder.databinding.ActivityIntroBinding;

public class IntroScreenActivity extends BaseActivity<ActivityIntroBinding> {
    ImageView[] dots = null;
    IntroAdapter introAdapter;

    @Override
    public ActivityIntroBinding getBinding() {
        return ActivityIntroBinding.inflate(LayoutInflater.from(this));
    }

    @Override
    public void getData() {
        dots = new ImageView[]{findViewById(R.id.cricle_1), findViewById(R.id.cricle_2), findViewById(R.id.cricle_3)};

        introAdapter = new IntroAdapter(this);
    }

    @Override
    public void initView() {
        binding.viewPager2.setAdapter(introAdapter);

        binding.viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changeContentInit(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void bindView() {
        binding.btnNext.setOnClickListener(view -> {
            binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);
        });

        binding.btnStart.setOnClickListener(view -> {
            goToNextScreen();
        });
    }

    private void changeContentInit(int position) {
        for (int i = 0; i < 3; i++) {
            if (i == position) dots[i].setImageResource(R.drawable.ic_dot_selected);
            else dots[i].setImageResource(R.drawable.ic_dot_not_select);
        }

        switch (position) {
            case 0:
            case 1:
                binding.btnNext.setVisibility(View.VISIBLE);
                binding.btnStart.setVisibility(View.GONE);
                break;
            case 2:
                binding.btnNext.setVisibility(View.GONE);
                binding.btnStart.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void goToNextScreen() {
        startActivity(new Intent(this, MainScreenActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeContentInit(binding.viewPager2.getCurrentItem());
    }
}