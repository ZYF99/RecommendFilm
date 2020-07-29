package com.xxx.recommendfilm.ui.notice;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentNoticeBinding;
import com.xxx.recommendfilm.model.notice.Notice;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;

public class NoticeFragment extends BaseFragment<FragmentNoticeBinding, NoticeViewModel> {

    NoticeRecyclerAdapter noticeRecyclerAdapter;

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

        viewModel.noticeList.observe(this, new Observer<List<Notice>>() {
            @Override
            public void onChanged(List<Notice> notices) {
                noticeRecyclerAdapter.replaceData(notices);
            }
        });

        //电影列表适配器
        noticeRecyclerAdapter = new NoticeRecyclerAdapter(this, R.layout.item_notice, true, new ArrayList<Notice>());
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
        viewModel.fetchMoticeList();
    }
}