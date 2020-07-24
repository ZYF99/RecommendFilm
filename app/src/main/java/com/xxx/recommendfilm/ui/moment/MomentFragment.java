package com.xxx.recommendfilm.ui.moment;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentNoticeBinding;
import com.xxx.recommendfilm.ui.base.BaseFragment;

public class MomentFragment extends BaseFragment<FragmentNoticeBinding, MomentViewModel> {

    @Override
    protected Class<MomentViewModel> getViewModelClazz() { return MomentViewModel.class; }

    @Override
    protected int getLayoutRes() { return R.layout.fragment_moment; }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}