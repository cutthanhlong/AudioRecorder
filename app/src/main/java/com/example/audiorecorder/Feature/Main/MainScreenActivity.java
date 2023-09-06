package com.example.audiorecorder.Feature.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.audiorecorder.Adapter.MainAdapter;
import com.example.audiorecorder.Base.BaseActivity;
import com.example.audiorecorder.Dialog.DialogExitApp;
import com.example.audiorecorder.Dialog.DialogRating;
import com.example.audiorecorder.Interface.IClickDialogExit;
import com.example.audiorecorder.Interface.IClickDialogRate;
import com.example.audiorecorder.R;
import com.example.audiorecorder.Utils.SharePrefUtils;
import com.example.audiorecorder.databinding.ActivityMainBinding;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class MainScreenActivity extends BaseActivity<ActivityMainBinding> {

    ArrayList<String> remoteRate = new ArrayList<String>(Arrays.asList("2", "4", "6", "8", "10"));
    ReviewManager manager;
    ReviewInfo reviewInfo;
    DialogExitApp dialogExitApp;
    String TAG = "MainScreenActivity";

    @Override
    public ActivityMainBinding getBinding() {
        return ActivityMainBinding.inflate(LayoutInflater.from(this));
    }

    @Override
    public void getData() {

    }

    @Override
    public void initView() {
        setColorItemNav(0);
        binding.viewPager.setOffscreenPageLimit(2);
        addFragment();
    }

    @Override
    public void bindView() {
        binding.lnRecord.setOnClickListener(view -> onNavClick(0));
        binding.lnPlayer.setOnClickListener(view -> onNavClick(1));

    }

    private void onNavClick(int numNavSelect) {
        setColorItemNav(numNavSelect);
        binding.viewPager.setCurrentItem(numNavSelect);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setColorItemNav(int numNavSelect) {
        binding.ivRecord.setImageDrawable(getDrawable(R.drawable.ic_record_sn));
        binding.ivPlayer.setImageDrawable(getDrawable(R.drawable.ic_player_sn));
        binding.tvRecord.setTextColor(getResources().getColor(R.color.color_90AADE));
        binding.tvPlayer.setTextColor(getResources().getColor(R.color.color_90AADE));

        switch (numNavSelect) {
            case 0:
                binding.ivRecord.setImageDrawable(getDrawable(R.drawable.ic_record_s));
                binding.tvRecord.setTextColor(getResources().getColor(R.color.color_FC4184));
                break;
            case 1:
                binding.ivPlayer.setImageDrawable(getDrawable(R.drawable.ic_player_s));
                binding.tvPlayer.setTextColor(getResources().getColor(R.color.color_FC4184));
                break;
        }
    }

    private void addFragment() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(mainAdapter);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        setColorItemNav(0);
                        break;
                    }
                    case 1: {
                        setColorItemNav(1);
                        break;
                    }
                    default:
                        setColorItemNav(0);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void exitApp() {
        dialogExitApp = new DialogExitApp(this, true, new IClickDialogExit() {
            @Override
            public void onExit() {
                if (dialogExitApp.isShowing()) {
                    dialogExitApp.dismiss();
                    finishAffinity();
                }
            }

            @Override
            public void onCancel() {
                if (dialogExitApp.isShowing()) {
                    dialogExitApp.dismiss();
                }
            }
        });

        dialogExitApp.show();
    }


    private void rateApp() {
        DialogRating ratingDialog = new DialogRating(MainScreenActivity.this, true);
        ratingDialog.init(new IClickDialogRate() {
            @Override
            public void send() {
                ratingDialog.dismiss();
                String uriText = "mailto:" + SharePrefUtils.email + "?subject=" + "Review for " + SharePrefUtils.subject + "&body=" + SharePrefUtils.subject + "\nRate : " + ratingDialog.getRating() + "\nContent: ";
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                try {
                    finishAffinity();
                    startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.Send_Email)));
                    SharePrefUtils.forceRated(MainScreenActivity.this);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainScreenActivity.this, getResources().getString(R.string.There_is_no), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void rating() {
                manager = ReviewManagerFactory.create(MainScreenActivity.this);
                Task<ReviewInfo> request = manager.requestReviewFlow();
                request.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        reviewInfo = task.getResult();
                        Log.e("ReviewInfo", "" + reviewInfo);
                        Task<Void> flow = manager.launchReviewFlow(MainScreenActivity.this, reviewInfo);
                        flow.addOnSuccessListener(unused -> {
                            SharePrefUtils.forceRated(MainScreenActivity.this);
                            ratingDialog.dismiss();
                            finishAffinity();
                        });
                    } else {
                        ratingDialog.dismiss();
                    }
                });
            }

            @Override
            public void later() {
                finishAffinity();
                ratingDialog.dismiss();
            }

        });
        try {
            ratingDialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        onNavClick(0);
        if (!SharePrefUtils.isRated(this)) {
            if (remoteRate.contains(String.valueOf(SharePrefUtils.getCountOpenApp(this)))) {
                rateApp();
            } else {
                exitApp();
            }
        } else {
            exitApp();
        }
    }
}