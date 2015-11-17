package com.codigodelsur.androidsampleproject;

import android.app.Application;
import android.content.Context;

import com.codigodelsur.androidsampleproject.util.Auth;
import com.codigodelsur.androidsampleproject.util.FontCache;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public class CdsSampleApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        CdsSampleApplication.context = getApplicationContext();

        FontCache.getInstance().addFont("exo_regular", "Exo2-Regular.otf");
        FontCache.getInstance().addFont("exo_semibold", "Exo2-SemiBold.otf");

        Auth.getInstance().setUsername(getString(R.string.username));
        Auth.getInstance().setKey(getString(R.string.key));
    }

    public static Context getAppContext() {
        return CdsSampleApplication.context;
    }
}
