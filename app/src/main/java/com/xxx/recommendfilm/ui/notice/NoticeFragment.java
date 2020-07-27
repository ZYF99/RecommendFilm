package com.xxx.recommendfilm.ui.notice;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentFilmBinding;
import com.xxx.recommendfilm.databinding.FragmentNoticeBinding;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.model.notice.Notice;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;
import com.xxx.recommendfilm.ui.moment.MomentRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NoticeFragment extends BaseFragment<FragmentNoticeBinding, NoticeViewModel> {

    NoticeRecyclerAdapter noticeRecyclerAdapter;
    List<Notice> noticeList = new ArrayList<>();


    @Override
    protected Class<NoticeViewModel> getViewModelClazz() {
        return NoticeViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_notice;
    }


    @Override
    protected void initView() {

        //电影列表适配器
        noticeRecyclerAdapter = new NoticeRecyclerAdapter(this, R.layout.item_notice, true, noticeList);
        binding.rvNotice.setAdapter(noticeRecyclerAdapter);

        //电影点击跳转详情
        noticeRecyclerAdapter.setOnCellClickListener(new BaseRecyclerAdapter.OnCellClickListener<Notice>() {
            @Override
            public void onCellClick(Notice notice) {

            }
        });

        //下拉刷新监听
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    protected void initData() {
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeList.add(new Notice());
        noticeRecyclerAdapter.notifyDataSetChanged();
    }
}