package com.codigodelsur.androidsampleproject;

import android.app.Application;

import com.codigodelsur.androidsampleproject.util.Auth;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public class CdsSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Auth.getInstance().setUsername(getString(R.string.username));
        Auth.getInstance().setKey(getString(R.string.key));
    }
}
