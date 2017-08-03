package com.geek.mvvmtest.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.util.SparseArray;
import android.view.View.OnClickListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by chenMeng on 2017/8/1.
 */

public class AdapterModule<T> {
    public ObservableArrayList<T> list = new ObservableArrayList<>();
    // bindingVaiable id(绑定的 T 实体类 BR)
    public ObservableInt bindingVaiable;
    // bind PositionId
    public ObservableInt bindPositionVaiableId;
    // the key is the resourceId!
    public WeakReference<SparseArray<OnClickListener>> listeners;

    public AdapterModule(ArrayList<T> list, int bindingVaiable, int bindPositionVaiableId) {
        if (list != null && !list.isEmpty()) {
            this.list.addAll(list);
        }
        this.bindingVaiable = new ObservableInt(bindingVaiable);
        this.bindPositionVaiableId = new ObservableInt(bindPositionVaiableId);
    }

    public AdapterModule(ArrayList<T> list, int bindingVaiable, SparseArray<OnClickListener> listeners) {
        if (list != null && !list.isEmpty()) {
            this.list.addAll(list);
        }
        this.bindingVaiable = new ObservableInt(bindingVaiable);
        this.listeners = new WeakReference<>(listeners);
    }

    public void setListeners(SparseArray<OnClickListener> listeners) {
        this.listeners = new WeakReference<>(listeners);
    }
}
