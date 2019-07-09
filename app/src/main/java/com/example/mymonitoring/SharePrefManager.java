package com.example.mymonitoring;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharePrefManager {
    private static SharePrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String FULLNAME = "fullname";
    private static final String EMAIL = "userEmail";
    private static final String ID = "userId";

    private SharePrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharePrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharePrefManager(context);
        }
        return mInstance;
    }
    // user log in method
    public boolean userLogin(int id, String fullname, String email){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(ID, id);
        editor.putString(EMAIL, email);
        editor.putString(FULLNAME, fullname);

        editor.apply();
        return true;
    }
    // after user logged in method
    public boolean userLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(FULLNAME, null) != null){
        return true;
        }
        return false;
    }

    // user log out logout
    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;

    }
}

