package com.example.audiorecorder.Feature.Permission;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.audiorecorder.Base.BaseActivity;
import com.example.audiorecorder.Feature.Main.MainScreenActivity;
import com.example.audiorecorder.R;
import com.example.audiorecorder.Utils.SpHelper;
import com.example.audiorecorder.databinding.ActivityPermissionBinding;

public class PermissionScreenActivity extends BaseActivity<ActivityPermissionBinding> {

    int REQUEST_PERMISSIONS_READ = 101;

    int countRead = 0;

    @Override
    public ActivityPermissionBinding getBinding() {
        return ActivityPermissionBinding.inflate(LayoutInflater.from(this));
    }

    @Override
    public void getData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {
        binding.tvContinue.setOnClickListener(v -> onNextStep());

        binding.ivSwitchReadPer.setOnClickListener(v -> addPermissionReadWrite());
    }

    private void onNextStep() {
        if (checkCameraPermission() && checkReadPermission()) {
            startActivity(new Intent(PermissionScreenActivity.this, MainScreenActivity.class));
            finish();
        } else {
            showDialogToastPer();
        }
    }

    private void showDialogToastPer() {
        Dialog dialogToastPer = new Dialog(this);
        dialogToastPer.setContentView(R.layout.dialog_toast_permission);
        dialogToastPer.getWindow().setGravity(Gravity.CENTER);
        dialogToastPer.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogToastPer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogToastPer.setCancelable(true);
        dialogToastPer.show();

        dialogToastPer.findViewById(R.id.tv_per_alert_cancel).setOnClickListener(view -> dialogToastPer.dismiss());

        dialogToastPer.findViewById(R.id.tv_per_alert_save).setOnClickListener(view -> {
//            startActivity(new Intent(PermissionScreenActivity.this, MainScreenActivity.class));
//            finish();
        });

    }

    private void addPermissionReadWrite() {
        if (!checkReadPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.READ_MEDIA_IMAGES,
                                Manifest.permission.READ_MEDIA_VIDEO,
                                Manifest.permission.READ_MEDIA_AUDIO
                        },
                        REQUEST_PERMISSIONS_READ
                );
            } else {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        REQUEST_PERMISSIONS_READ
                );
            }
        }
        if (countRead > 1) {
            if (checkReadPermission()) {
                SpHelper.setInt(this, SpHelper.STORAGE, 0);
                return;
            }
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    private boolean checkReadPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            SpHelper.setInt(this, SpHelper.STORAGE, 0);
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    SpHelper.setInt(this, SpHelper.STORAGE, 0);
                    return true;
                }
            }
            return false;
        }
    }

    private boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            SpHelper.setInt(this, SpHelper.CAMERA, 0);
            return true;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    SpHelper.setInt(this, SpHelper.CAMERA, 0);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS_READ) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SpHelper.getInt(this, SpHelper.COUNT_PERMISSION_STORAGE, 1);
                SpHelper.setInt(this, SpHelper.STORAGE, 0);
                binding.ivSwitchReadPer.setImageResource(R.drawable.ic_switch_s);
            } else {
                countRead++;
                SpHelper.setInt(this, SpHelper.STORAGE, countRead);
                if (countRead > 1) {
                    SpHelper.setInt(this, SpHelper.COUNT_PERMISSION_STORAGE_PEMISSION, 1);
                }
                binding.ivSwitchReadPer.setImageResource(R.drawable.ic_switch_sn);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
