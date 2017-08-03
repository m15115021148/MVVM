package com.geek.mvvmtest.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.geek.mvvmtest.adapter.AdapterModule;
import com.geek.mvvmtest.model.StudentModel;
import com.geek.mvvmtest.model.UserModel;

import java.util.List;

/**
 * Created by chenMeng on 2017/8/1.
 */

public class RecylceVM {
    private ObservableField<AdapterModule<StudentModel>> mBinding;
    private AdapterModule<StudentModel> adapterModule;
    private Context mContext;

    public RecylceVM(Context context,AdapterModule<StudentModel> model){
        this.mContext = context;
        this.adapterModule = model;
        mBinding = new ObservableField<>();
        mBinding.set(model);
    }

    public void onClick(int position){
        Toast.makeText(mContext,"点击"+position,Toast.LENGTH_SHORT).show();
    }










    public AdapterModule<StudentModel> getAdapterModule() {
        return adapterModule;
    }
}
