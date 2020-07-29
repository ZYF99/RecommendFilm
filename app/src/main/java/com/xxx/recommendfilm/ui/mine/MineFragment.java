package com.xxx.recommendfilm.ui.mine;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.Observer;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentMineBinding;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.moment.MomentRecyclerAdapter;
import com.xxx.recommendfilm.ui.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MineFragment extends BaseFragment<FragmentMineBinding, MineViewModel> {

    MomentRecyclerAdapter momentRecyclerAdapter;


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

        viewModel.momentListLiveData.observe(this, new Observer<List<Moment>>() {
            @Override
            public void onChanged(List<Moment> moments) {
                momentRecyclerAdapter.replaceData(moments);
            }
        });

        //电影列表适配器
        momentRecyclerAdapter = new MomentRecyclerAdapter(this, R.layout.item_moment, true, new ArrayList<Moment>());
        binding.rvMyMoments.setAdapter(momentRecyclerAdapter);

        binding.imSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SettingActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getUserProfile();
        viewModel.fetchMyMomentsList();
    }
}