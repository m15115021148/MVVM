package com.geek.mvvmtest.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.geek.mvvmtest.BR;
import com.geek.mvvmtest.R;
import com.geek.mvvmtest.adapter.AdapterModule;
import com.geek.mvvmtest.adapter.MyRecyclerAdapter;
import com.geek.mvvmtest.databinding.ActivityRecyclerViewBinding;
import com.geek.mvvmtest.model.StudentModel;
import com.geek.mvvmtest.model.UserModel;
import com.geek.mvvmtest.vm.RecylceVM;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    private ActivityRecyclerViewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view);

        ArrayList<StudentModel> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            StudentModel model = new StudentModel();
            model.setGrade(String.valueOf(90+i));
            list.add(model);
        }

        SparseArray<View.OnClickListener> listener = new SparseArray<>();
        listener.put(1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        AdapterModule<StudentModel> userModel = new AdapterModule<>(list, BR.student,BR.position);

        userModel.setListeners(listener);

        RecylceVM vm = new RecylceVM(this,userModel);

        mBinding.setLayoutId(R.layout.recylce_view_layout_item);
        mBinding.setRecyclerVm(vm);
    }
}
