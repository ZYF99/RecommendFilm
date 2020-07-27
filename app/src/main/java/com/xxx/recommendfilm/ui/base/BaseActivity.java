package com.xxx.recommendfilm.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.xxx.recommendfilm.BR;
import com.xxx.recommendfilm.MainApplication;
import com.xxx.recommendfilm.util.DialogUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseActivity<Bind extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    //获取当前ViewModel的类型Class对象，用于初始化ViewModel对象
    protected abstract Class<VM> getViewModelClazz();

    //获取当前界面的layout资源
    protected abstract int getLayoutRes();

    //每个界面Binding对象
    protected Bind binding;

    //每个界面的ViewModel对象
    protected VM viewModel;

    //初始化
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = new ViewModelProvider(this).get(getViewModelClazz());
        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
        initView();
        initData();
        observeEvent();
    }

    //加载界面控件
    protected abstract void initView();

    //加载界面初始化数据
    protected abstract void initData();

    //添加基类的事件的监听
    private void observeEvent() {
        viewModel.isShowLoadingProgress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(final Boolean aBoolean) {
                    AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                        @Override
                        public void run() {
                            if (aBoolean) DialogUtil.getInstance().showProgressDialog(BaseActivity.this);
                            else DialogUtil.getInstance().hideProgressDialog();
                        }
                    });
            }
        });
    }
}
