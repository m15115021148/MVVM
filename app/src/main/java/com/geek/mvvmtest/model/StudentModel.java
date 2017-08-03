package com.geek.mvvmtest.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.geek.mvvmtest.BR;

/**
 * Created by chenMeng on 2017/8/2.
 */

public class StudentModel extends BaseObservable{
    @Bindable
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
        notifyPropertyChanged(BR.grade);
    }
}
