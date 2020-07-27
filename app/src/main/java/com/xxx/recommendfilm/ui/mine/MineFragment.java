package com.xxx.recommendfilm.ui.mine;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentMineBinding;
import com.xxx.recommendfilm.ui.base.BaseFragment;

public class MineFragment extends BaseFragment<FragmentMineBinding, MineViewModel> {

    @Override
    protected Class<MineViewModel> getViewModelClazz() {
        return MineViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}