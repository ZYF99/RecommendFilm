package com.xxx.recommendfilm.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.xxx.recommendfilm.R;

public class GlideUtil {
    public static void loadImageFromUrl(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
