package com.geek.mvvmtest.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geek.mvvmtest.BR;
import com.geek.mvvmtest.R;
import com.geek.mvvmtest.model.StudentModel;

import java.util.List;

/**
 * Created by chenMeng on 2017/8/2.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{
    private List<StudentModel> list;
    private Context context;
    private LayoutInflater inflater;

    public MyRecyclerAdapter(List<StudentModel> list,Context context){
        this.list = list;
        this.context = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.recylce_view_layout_item, null, false);
        MyViewHolder holder = new MyViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.binding.setVariable(BR.student,list.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
