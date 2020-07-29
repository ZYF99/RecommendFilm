package com.xxx.recommendfilm.ui.moment;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentMomentBinding;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;

public class MomentFragment extends BaseFragment<FragmentMomentBinding, MomentViewModel> {

    MomentRecyclerAdapter momentRecyclerAdapter;

    @Override
    protected Class<MomentViewModel> getViewModelClazz() {
        return MomentViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_moment;
    }


    @Override
    protected void initView() {

        viewModel.momentList.observe(this, new Observer<List<Moment>>() {
            @Override
            public void onChanged(List<Moment> moments) {
                momentRecyclerAdapter.replaceData(moments);
            }
        });

        //电影列表适配器
        momentRecyclerAdapter = new MomentRecyclerAdapter(this, R.layout.item_moment, true, new ArrayList<Moment>());
        binding.rvMoment.setAdapter(momentRecyclerAdapter);

        //电影点击跳转详情
        momentRecyclerAdapter.setOnCellClickListener(new BaseRecyclerAdapter.OnCellClickListener<Moment>() {
            @Override
            public void onCellClick(Moment Moment) {

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
        viewModel.fetchMyMomentsList();
    }
}