package com.geek.mvvmtest.util;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by chenMeng on 2017/7/31.
 */

public class BindingUtil {

    /**
     * 第一个参数必须是view
     * @param imageView
     * @param imageUrl
     */
    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null) {
            Picasso.with(imageView.getContext())
                    .load(imageUrl)
                    .into(imageView);
        }
    }

    @BindingAdapter({"imageShow","isShow"})
    public static void loadImageIsShow(ImageView imageView, String imageUrl,boolean isShow) {
        if (imageUrl != null) {
            if (isShow){
                Picasso.with(imageView.getContext())
                        .load(imageUrl)
                        .into(imageView);
            }else{
                Toast.makeText(imageView.getContext(),"不显示",Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 拼接
     * @param view
     * @param msg
     */
    @BindingAdapter({"text"})
    public static void showMsg(TextView view,String msg){
        view.setText(msg);
    }
}
