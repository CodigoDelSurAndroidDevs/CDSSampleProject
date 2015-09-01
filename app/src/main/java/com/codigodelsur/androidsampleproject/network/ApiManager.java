package com.codigodelsur.androidsampleproject.network;

import com.codigodelsur.androidsampleproject.response.HighResResponse;
import com.codigodelsur.androidsampleproject.util.Auth;
import com.codigodelsur.androidsampleproject.util.Constants;

import retrofit.Callback;
import retrofit.RestAdapter;


/**
 * Created by marcosambrosi on 9/1/15.
 */
public class ApiManager {


    private static ImageService sService;
    private static ApiManager sInstance;

    static {
        initializeService();
    }

    private ApiManager() {

    }

    private static void initializeService() {

        RestAdapter.Builder restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL);

        sService = restAdapter.build().create(ImageService.class);
    }

    public static ApiManager getInstance() {
        if (sInstance == null) {
            sInstance = new ApiManager();
        }
        return sInstance;
    }

    /**
     * Search for high resolution images
     *
     * @param query
     * @param callback
     */
    public void searchHighRes(String query, Callback<HighResResponse> callback) {
        sService.searchHighRes(
                Auth.getInstance().getUsername(),
                Auth.getInstance().getKey(),
                "high_resolution",
                query, callback);
    }

    /**
     * Search for images
     *
     * @param query
     * @param callback
     */
    public void search(String query, int page, int resultsPerPage, Callback<HighResResponse> callback) {
        sService.search(
                Auth.getInstance().getUsername(),
                Auth.getInstance().getKey(),
                "photo",
                query, page, resultsPerPage, callback);
    }

}
