package com.geek.mvvmtest.adapter;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.geek.mvvmtest.model.StudentModel;
import com.geek.mvvmtest.model.UserModel;

import java.lang.ref.WeakReference;

/**
 * Created by chenMeng on 2017/8/1.
 */

public class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewAdapter.BindingHolder> {
    private static final Object DATA_INVALIDATION = new Object();

    @SuppressLint("SupportAnnotationUsage")
    public @LayoutRes ObservableInt layoutId;

    private AdapterModule adapterModule;

    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);
    private LayoutInflater inflater;

    @Nullable
    private RecyclerView recyclerView;

    public BaseRecycleViewAdapter(int layoutId) {
        this.layoutId = new ObservableInt(layoutId);
    }

    public void setAdapterModule(AdapterModule<T> adapterModule) {
        if (this.adapterModule == adapterModule || adapterModule == null) {
            return;
        }

        if (recyclerView != null) {
            if (this.adapterModule != null && this.adapterModule.list != null && this.adapterModule.list instanceof ObservableArrayList) {
                this.adapterModule.list.removeOnListChangedCallback(callback);
            }
            if (adapterModule != null && adapterModule.list != null && adapterModule.list instanceof ObservableList) {
                adapterModule.list.addOnListChangedCallback(callback);
            }
        }

        this.adapterModule = adapterModule;
        notifyDataSetChanged();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId.get(), null, false);
        final BindingHolder holder = new BindingHolder(binding, (SparseArray<OnClickListener>) adapterModule.listeners.get());

        binding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                return recyclerView != null && recyclerView.isComputingLayout();
            }

            @Override
            public void onCanceled(ViewDataBinding binding) {
                if (recyclerView == null || recyclerView.isComputingLayout()) {
                    return;
                }
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    notifyItemChanged(position, DATA_INVALIDATION);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
//        holder.binding.setVariable(adapterModule.bindingVaiable.get(), adapterModule.list.get(position));
//        holder.binding.executePendingBindings();
        onBindBinding(holder.binding, adapterModule.bindPositionVaiableId.get(), position, false);
        onBindBinding(holder.binding, adapterModule.bindingVaiable.get(), adapterModule.list.get(position), true);
    }

    private void onBindBinding(ViewDataBinding binding, int bindingVariable, Object item, boolean executePendingBindings) {
        if (bindingVariable != 0) {
            boolean result = binding.setVariable(bindingVariable, item);

            if (!result) {
                throw new IllegalArgumentException("can't bind variable adapterModule because can't find the id,is it correct?");
            }
            // refresh
            if (executePendingBindings) {
                binding.executePendingBindings();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (this.adapterModule == null) {
            return 0;
        }
        return this.adapterModule.list.size();
    }

    public static class BindingHolder<T> extends RecyclerView.ViewHolder{

        ViewDataBinding binding;
        private SparseArray<OnClickListener> listeners;

        public BindingHolder(ViewDataBinding binding, SparseArray<OnClickListener> listeners) {
            super(binding.getRoot());
            this.binding = binding;
            this.listeners = listeners;
            onBindListeners();
        }

        public void onBindListeners() {
            if (listeners != null && listeners.size() > 0) {
                for (int i = 0; i < listeners.size(); i++) {
                    binding.setVariable(listeners.keyAt(i), listeners.get(listeners.keyAt(i)));
                }
            }
        }
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableArrayList.OnListChangedCallback<ObservableArrayList<T>>{
        WeakReference<BaseRecycleViewAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BaseRecycleViewAdapter<T> adapter){
            this.adapterRef = new WeakReference<>(adapter);
        }

        @Override
        public void onChanged(ObservableArrayList<T> ts) {
            BaseRecycleViewAdapter<T> adapter = adapterRef.get();
            if (adapter == null) return;
//            Utils.ensureChangeOnMainThread();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableArrayList<T> ts, int i, int i1) {

        }

        @Override
        public void onItemRangeInserted(ObservableArrayList<T> ts, int i, int i1) {

        }

        @Override
        public void onItemRangeMoved(ObservableArrayList<T> ts, int i, int i1, int i2) {

        }

        @Override
        public void onItemRangeRemoved(ObservableArrayList<T> ts, int i, int i1) {

        }
    }
}
