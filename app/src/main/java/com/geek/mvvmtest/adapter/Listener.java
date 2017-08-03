package com.geek.mvvmtest.adapter;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chenMeng on 2017/8/1.
 */

public class Listener {
    private Context mContext;

    public Listener(Context context){
        this.mContext = context;
    }


    public void onClick(int position){
        Toast.makeText(mContext,"点击"+position,Toast.LENGTH_SHORT).show();
    }
}
