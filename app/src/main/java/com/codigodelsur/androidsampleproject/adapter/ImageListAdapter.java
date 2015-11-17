package com.codigodelsur.androidsampleproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codigodelsur.androidsampleproject.R;
import com.codigodelsur.androidsampleproject.model.Image;
import com.codigodelsur.androidsampleproject.util.ColorUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        imageViewHolder.imageViewBackground = (ImageView) itemView.findViewById(R.id.image);
        imageViewHolder.imageViewProfile = (CircleImageView) itemView.findViewById(R.id.profile_image);
        imageViewHolder.infoContainer = itemView.findViewById(R.id.info_container);
        imageViewHolder.imageViewProfile.bringToFront();

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder imageViewHolder, int i) {
        Image image = mImages.get(i);

        imageViewHolder.imageViewBackground.setBackgroundColor(ColorUtil.getRandomColor());
        Context context = imageViewHolder.itemView.getContext();

        try {

            Picasso.with(context).
                    load(image.webformatUrl).
                    into(imageViewHolder.imageViewBackground, new Callback() {
                        @Override
                        public void onSuccess() {
                            imageViewHolder.applyPalette();
                        }

                        @Override
                        public void onError() {

                        }
                    });

            Picasso.with(context).
                    load(image.userImageUrl).
                    into(imageViewHolder.imageViewProfile);

        } catch (IllegalArgumentException e) {
            //Path is empty
        }


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

        public ImageView imageViewBackground;
        public CircleImageView imageViewProfile;
        public View infoContainer;

        private void applyPalette() {
            final Bitmap bitmap = ((BitmapDrawable) imageViewBackground.
                    getDrawable()).
                    getBitmap();

            new Palette.Builder(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();

                    if (vibrantSwatch != null) {
                        infoContainer.setBackgroundColor(vibrantSwatch.getRgb());
                    }
                }
            });

        }
    }
}
