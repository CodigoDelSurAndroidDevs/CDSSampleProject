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

        FontCache.getInstance().addFont("toolbar_font", "Pacifico.ttf");
        FontCache.getInstance().addFont("roboto_regular", "Roboto-Regular.ttf");
        FontCache.getInstance().addFont("roboto_medium", "Roboto-Medium.ttf");
        FontCache.getInstance().addFont("roboto_bold", "Roboto-Bold.ttf");


        Auth.getInstance().setUsername(getString(R.string.username));
        Auth.getInstance().setKey(getString(R.string.key));
    }

    public static Context getAppContext() {
        return CdsSampleApplication.context;
    }
}
