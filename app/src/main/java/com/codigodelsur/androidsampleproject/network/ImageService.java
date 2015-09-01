package com.codigodelsur.androidsampleproject.network;

import com.codigodelsur.androidsampleproject.response.HighResResponse;

import retrofit.Callback;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public interface ImageService {

    @GET("/api/")
    void searchHighRes(@Query("username") String username,
                           @Query("key") String key,
                           @Query("response_group") String responseGroup,
                           @Query("q") String query,
                           Callback<HighResResponse> callback);  @GET("/api/")

    void search(@Query("username") String username,
                           @Query("key") String key,
                           @Query("image_type") String imageType,
                           @Query("q") String query,
                           Callback<HighResResponse> callback);
}
