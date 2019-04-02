package com.cyrj.pharmacymessage.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;

/**
 * 图片加载公共类
 */

public class GlideUtils {

    public static void loadImageView( Object path, ImageView mImageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)	//加载成功之前占位图
                .error(R.mipmap.ic_launcher)	//加载错误之后的错误图
                //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                .fitCenter()
                //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
                .skipMemoryCache(true)	//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)	//缓存所有版本的图像
                .diskCacheStrategy(DiskCacheStrategy.NONE)	//跳过磁盘缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)	//只缓存最终的图片
                ;
        Glide.with(MyApplication.getContext()).load(path).apply(options).into(mImageView);
    }
}
