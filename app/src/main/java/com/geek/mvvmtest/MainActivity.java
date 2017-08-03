package com.geek.mvvmtest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geek.mvvmtest.databinding.ActivityMainBinding;
import com.geek.mvvmtest.model.UserModel;
import com.geek.mvvmtest.vm.UserVM;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        UserModel model = new UserModel();
        model.setAge("22");
        model.setUserName("cmm");
        model.setUrl("http://192.168.2.103:8989/MySSM/upload/photo/bda0de7b83ee436aa9a8c9ae0711b25e.jpg");
        model.setShow(true);

        UserVM vm = new UserVM(this,model);
        mBinding.setVm(vm);
//        mBinding.setUser(vm.getUser());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
    }
}
