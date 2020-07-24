package com.xxx.recommendfilm.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xxx.recommendfilm.BR;

/*Fragment的基类*/
public abstract class BaseFragment<Bind extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    //获取当前ViewModel的类型Class对象，用于初始化ViewModel对象
    protected abstract Class<VM> getViewModelClazz();

    //获取当前界面的layout资源
    @LayoutRes
    protected abstract int getLayoutRes();

    //每个界面Binding对象
    private Bind binding;

    //每个界面的ViewModel对象
    private VM viewModel;

    //初始化
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(getViewModelClazz());
    }

    //初始化界面的View
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return binding.getRoot();
    }

    //初始化View完成后
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
        initView();
        initData();
    }

    //加载界面控件
    protected abstract void initView();

    //加载界面初始化数据
    protected abstract void initData();
}

