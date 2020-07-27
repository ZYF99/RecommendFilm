package com.xxx.recommendfilm.ui.moment;

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
    List<Moment> momentList = new ArrayList<>();

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

        //电影列表适配器
        momentRecyclerAdapter = new MomentRecyclerAdapter(this, R.layout.item_moment, true, momentList);
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
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentList.add(new Moment());
        momentRecyclerAdapter.notifyDataSetChanged();
    }
}