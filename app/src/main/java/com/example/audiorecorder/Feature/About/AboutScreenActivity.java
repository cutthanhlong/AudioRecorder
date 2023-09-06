package com.example.audiorecorder.Feature.About;

import android.view.LayoutInflater;

import com.example.audiorecorder.Base.BaseActivity;
import com.example.audiorecorder.BuildConfig;
import com.example.audiorecorder.R;


public class AboutScreenActivity extends BaseActivity<ActivityAboutBinding> {

    @Override
    public ActivityAboutBinding getBinding() {
        return ActivityAboutBinding.inflate(LayoutInflater.from(this));
    }

    @Override
    public void getData() {

    }

    @Override
    public void initView() {
        setStatusBarGradiant(this, R.drawable.bg_app);

        binding.tvVersion.setText(getString(R.string.version) + " " + BuildConfig.VERSION_NAME);
    }

    @Override
    public void bindView() {
        binding.btnBack.setOnClickListener(view -> onBackPressed());

        binding.btnPolicy.setOnClickListener(view -> {
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("link")));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
