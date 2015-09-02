package com.codigodelsur.androidsampleproject.response;

import com.codigodelsur.androidsampleproject.model.Image;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public class ImageSearchResponse {
    public long total;
    public long totalHits;

    @SerializedName("hits")
    public List<Image> images;
}
