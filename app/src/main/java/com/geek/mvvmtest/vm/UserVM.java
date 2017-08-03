package com.geek.mvvmtest.vm;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.util.Log;

import com.geek.mvvmtest.model.UserModel;
import com.geek.mvvmtest.ui.RecyclerViewActivity;
import com.geek.mvvmtest.util.BindingUtil;

/**
 * Created by chenMeng on 2017/7/31.
 */

public class UserVM {
    private ObservableField<UserModel> bindingUser;
    private UserModel user;
    private Activity mContext;

    public UserVM(Activity activity,UserModel model){
        this.mContext = activity;
        this.user = model;
        bindingUser = new ObservableField<>();
        bindingUser.set(model);
    }

    public void onClickUser(){
        bindingUser.get().setAge(String.valueOf(Integer.parseInt(bindingUser.get().getAge())+1));
        Intent intent = new Intent(mContext, RecyclerViewActivity.class);
        mContext.startActivity(intent);
    }

    public void onClickTestUpdate(){
        bindingUser.get().setUrl("http://192.168.2.103:8989/MySSM/upload/photo/63462e172f554f4d815e35a853a87f1e.jpg");
        bindingUser.get().setShow(false);
    }






    public UserModel getUser() {
        return user;
    }

}
