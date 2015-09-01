package com.codigodelsur.androidsampleproject.util;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public class Auth {

    private static Auth sInstance;


    private String mUsername;
    private String mKey;

    private Auth() {
    }

    public static Auth getInstance() {
        if (sInstance == null) {
            sInstance = new Auth();
        }
        return sInstance;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }
}
