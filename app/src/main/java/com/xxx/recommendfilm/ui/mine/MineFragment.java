package com.xxx.recommendfilm.ui.mine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.Observer;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentMineBinding;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.moment.MomentRecyclerAdapter;
import com.xxx.recommendfilm.ui.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Action;

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

        viewModel.momentListLiveData.observe(this, moments -> momentRecyclerAdapter.replaceData(moments));

        //电影列表适配器
        momentRecyclerAdapter = new MomentRecyclerAdapter(this, R.layout.item_moment, true, new ArrayList<Moment>());
        binding.rvMyMoments.setAdapter(momentRecyclerAdapter);

        binding.imSetting.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingActivity.class)));

        //用户反馈啊按钮
        binding.btnFeedback.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_feedback, null);
            AlertDialog d = new AlertDialog.Builder(getContext()).setView(dialogView)
                    .setPositiveButton("取消", (dialog1, which) -> dialog1.dismiss())
                    .create();
            dialogView.findViewById(R.id.btn_feedback).setOnClickListener(v1 -> {
                String content = ((EditText) dialogView.findViewById(R.id.et_feedback_content)).getText().toString();
                viewModel.feedBack(content, () -> {

                    AlertDialog.Builder sd = new AlertDialog.Builder(getContext());
                    AlertDialog dialog = sd.setMessage("感谢您的反馈，我们将尽快处理！")
                            .setPositiveButton("取消", (dialog1, which) -> {
                                dialog1.dismiss();
                                d.dismiss();
                            })
                            .create();
                    dialog.show();
                });
            });
            d.show();
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