package com.xxx.recommendfilm.ui.home;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityLoginBinding;
import com.xxx.recommendfilm.databinding.ActivityMainBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, ActivityMainViewModel> {

    @Override
    protected Class<ActivityMainViewModel> getViewModelClazz() {
        return ActivityMainViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void setUpBottomNavigation(){
        //binding.bottomNavigation.
    }



    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}