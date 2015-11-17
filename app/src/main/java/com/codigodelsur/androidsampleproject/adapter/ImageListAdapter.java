package com.codigodelsur.androidsampleproject.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codigodelsur.androidsampleproject.R;
import com.codigodelsur.androidsampleproject.model.Image;
import com.codigodelsur.androidsampleproject.util.ColorUtil;
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

        imageViewHolder.userName = (TextView) itemView.findViewById(R.id.user_name);
        imageViewHolder.favorites = (TextView) itemView.findViewById(R.id.text_view_favorites);
        imageViewHolder.comments = (TextView) itemView.findViewById(R.id.text_view_comments);
        imageViewHolder.downloads = (TextView) itemView.findViewById(R.id.text_view_downloads);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder imageViewHolder, int i) {
        Image image = mImages.get(i);

        imageViewHolder.imageViewBackground.setBackgroundColor(ColorUtil.getRandomColor());

        imageViewHolder.userName.setText(image.userName);

        //Counters
        imageViewHolder.favorites.setText(withSuffix(image.favoritesCount));
        imageViewHolder.comments.setText(withSuffix(image.commentsCount));
        imageViewHolder.downloads.setText(withSuffix(image.downloadsCount));


        //Load images from server
        try {

            Context context = imageViewHolder.itemView.getContext();

            Picasso.with(context).
                    load(image.webformatUrl).
                    into(imageViewHolder.imageViewBackground);

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
            DataBindingUtil.bind(itemView);
        }

        public TextView userName;
        public TextView favorites;
        public TextView comments;
        public TextView downloads;
        public ImageView imageViewBackground;
        public CircleImageView imageViewProfile;
        public View infoContainer;
    }

    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp - 1));
    }

}
