package com.codigodelsur.androidsampleproject.network;

import com.codigodelsur.androidsampleproject.response.HighResResponse;

import retrofit.Callback;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public interface ImageService {

    @GET("https://pixabay.com/api/")
    void searchHighRes(@Query("username") String username,
                           @Query("key") String key,
                           @Query("response_group") String responseGroup,
                           @Query("q") String query,
                           Callback<HighResResponse> callback);
}
