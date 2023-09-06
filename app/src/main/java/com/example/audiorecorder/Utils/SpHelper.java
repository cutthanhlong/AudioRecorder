package com.example.audiorecorder.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpHelper {
    public static final String PREFERENCE = "2023";
    public static String CAMERA = "CAMERA: ";
    public static String STORAGE = "STORAGE: ";
    public static String MICROPHONE = "MICROPHONE: ";
    public static String CONTACT = "CONTACT: ";
    public static String THEME = "THEME: ";
    public static String OVERLAY = "OVERLAY: ";
    public static String IMAGE_STRING = "IMAGE_STRING: ";
    public static String FILE_PATH = "FILE_PATH: ";
    public static String IMAGE_CAMERA_STRING = "IMAGE_CAMERA_STRING: ";
    public static String COUNT_PERMISSION_STORAGE = "COUNT_PERMISSION_STORAGE: ";
    public static String COUNT_PERMISSION_CAMERA = "COUNT_PERMISSION_CAMERA: ";
    public static String COUNT_PERMISSION_STORAGE_PEMISSION = "COUNT_PERMISSION_STORAGE_PEMISSION: ";
    public static String COUNT_PERMISSION_CAMERA_PEMISSION = "COUNT_PERMISSION_CAMERA_PEMISSION: ";

    public static final String SHARED_PREFS_NAME = "key_walkaround_settings";


    private void SharedPreferencesUtils() {
        //Do Something
    }

    /*
     * Util method to get SharedPreference.
     */
    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void putString(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFERENCE, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String getString(Context context, String str, String str2) {
        return context.getSharedPreferences(PREFERENCE, 0).getString(str, str2);
    }

    public static void setLong(Context context, String str, long i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFERENCE, 0).edit();
        edit.putLong(str, i);
        edit.apply();
    }

    public static long getLong(Context context, String str, long i) {
        return context.getSharedPreferences(PREFERENCE, 0).getLong(str, i);
    }

    public static void setInt(Context context, String str, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFERENCE, 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static int getInt(Context context, String str, int i) {
        return context.getSharedPreferences(PREFERENCE, 0).getInt(str, i);
    }

    public static void setBoolean(Context context, String str, boolean b) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFERENCE, 0).edit();
        edit.putBoolean(str, b);
        edit.apply();
    }

    public static boolean getBoolean(Context context, String str, boolean b) {
        return context.getSharedPreferences(PREFERENCE, 0).getBoolean(str, b);
    }
}
