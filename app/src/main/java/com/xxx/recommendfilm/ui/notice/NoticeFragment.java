package com.xxx.recommendfilm.ui.notice;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentFilmBinding;
import com.xxx.recommendfilm.databinding.FragmentNoticeBinding;
import com.xxx.recommendfilm.ui.base.BaseFragment;

public class NoticeFragment extends BaseFragment<FragmentNoticeBinding, NoticeViewModel> {

    @Override
    protected Class<NoticeViewModel> getViewModelClazz() { return NoticeViewModel.class; }

    @Override
    protected int getLayoutRes() { return R.layout.fragment_notice; }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}