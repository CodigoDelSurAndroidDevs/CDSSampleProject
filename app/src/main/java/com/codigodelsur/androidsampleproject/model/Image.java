package com.codigodelsur.androidsampleproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public class Image {
    @SerializedName("previewURL")
    public String previewUrl;

    @SerializedName("webformatURL")
    public String webformatUrl;


    @SerializedName("user")
    public String userName;

    @SerializedName("userImageURL")
    public String userImageUrl;


    @SerializedName("views")
    public long favoritesCount;

    @SerializedName("comments")
    public long commentsCount;

    @SerializedName("downloads")
    public long downloadsCount;

}
