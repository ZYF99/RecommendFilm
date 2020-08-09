package com.xxx.recommendfilm.ui.notice;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
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

        viewModel.isRefreshing.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.refreshLayout.setRefreshing(aBoolean);
            }
        });

        viewModel.noticeListLiveData.observe(this, filmList -> {
            noticeRecyclerAdapter.replaceData(filmList);
            binding.refreshLayout.setRefreshing(false);
        });

        //电影列表适配器
        noticeRecyclerAdapter = new NoticeRecyclerAdapter(this, R.layout.item_notice, true, new ArrayList<Notice>());
        binding.rvNotice.setAdapter(noticeRecyclerAdapter);

        //电影点击跳转详情
        noticeRecyclerAdapter.setOnCellClickListener(notice -> {

        });

        //下拉刷新监听
        binding.refreshLayout.setOnRefreshListener(() -> viewModel.refreshNoticeList());

        //上拉加载
        binding.rvNotice.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (viewModel.noticePageModelMutableLiveData.getValue() != null) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (!viewModel.isLoadingMore.getValue()
                                && viewModel.noticePageModelMutableLiveData.getValue().getPages() > 1
                                && !viewModel.noticePageModelMutableLiveData.getValue().isLastPage()
                        ) {
                            viewModel.loadMoreNoticeList();
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void initData() {
        viewModel.refreshNoticeList();
    }
}