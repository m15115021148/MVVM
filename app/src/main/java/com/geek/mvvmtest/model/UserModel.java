package com.geek.mvvmtest.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.geek.mvvmtest.BR;


/**
 * Created by chenMeng on 2017/7/31.
 */

public class UserModel extends BaseObservable{
    @Bindable
    private String userName;
    @Bindable
    private String age;
    @Bindable
    private String url;
    @Bindable
    private boolean show;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
        notifyPropertyChanged(BR.show);
    }
}
