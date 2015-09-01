package com.codigodelsur.androidsampleproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codigodelsur.androidsampleproject.R;
import com.codigodelsur.androidsampleproject.model.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosambrosi on 9/1/15.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    List<Image> mImages = new ArrayList<>(10);

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflater.inflate(R.layout.image_cell, viewGroup, false);

        ImageViewHolder imageViewHolder = new ImageViewHolder(itemView);
        imageViewHolder.imageView = (ImageView) itemView.findViewById(R.id.image);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder imageViewHolder, int i) {
        Image image = mImages.get(i);

        Picasso.with(imageViewHolder.itemView.getContext()).
                load(image.webformatUrl).
                into(imageViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }


    public void addAll(List<Image> images) {
        mImages.clear();
        mImages.addAll(images);
        notifyDataSetChanged();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageViewHolder(View itemView) {
            super(itemView);
        }

        public ImageView imageView;
    }
}
