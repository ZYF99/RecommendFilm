package com.xxx.recommendfilm.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.xxx.recommendfilm.R;

public class ImageUtil {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.color.white)
                .skipMemoryCache(false)
                .centerCrop()
                .into(imageView);
    }
}

