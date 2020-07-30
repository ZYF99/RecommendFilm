package com.xxx.recommendfilm.ui.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

public class PictureSelectUtil {

    public static final int REQUESTCODE_AVATAR = 26725;
    public static final int REQUESTCODE_BACKGROUND = 26726;

    //打开选头像界面
    public static void showAvatarAlbum(Activity activity) {
        //参数很多，根据需要添加
        Matisse.from(activity)
                .choose(MimeType.ofImage()) // 选择 mime 的类型
                .showSingleMediaType(true)
                .countable(false)
                .maxSelectable(1) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUESTCODE_AVATAR); // 设置作为标记的请求码
    }

    //打开选背景界面
    public static void showBackgroundAlbum(Activity activity) {
        //参数很多，根据需要添加
        Matisse.from(activity)
                .choose(MimeType.ofImage()) // 选择 mime 的类型
                .showSingleMediaType(true)
                .countable(false)
                .maxSelectable(1) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUESTCODE_BACKGROUND); // 设置作为标记的请求码
    }

}
