package com.xxx.recommendfilm.ui.setting;

import android.view.View;

import androidx.lifecycle.Observer;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivitySettingBinding;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.ui.base.BaseActivity;

import java.util.Objects;

public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingViewModel> {

    @Override
    protected Class<SettingViewModel> getViewModelClazz() {
        return SettingViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }


    @Override
    protected void initView() {
        viewModel.userProfileLiveData.observe(this, new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile userProfile) {
                updateUserInfoUI(userProfile);
            }
        });
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.llSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.tvSex.getText().equals("女")) binding.tvSex.setText("男");
                else binding.tvSex.setText("女");
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "男";
                if (binding.tvSex.equals("女")) gender = "M";
                else gender = "F";
                viewModel.updateUserProfile(
                        "https://w.wallhaven.cc/full/39/wallhaven-39pw6v.jpg",
                        "https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/08/07/ChMkJl3FGRKIKoLpAAZs15rr_MoAAvHKAOi34gABmzv861.jpg",
                        25316412647l,
                        gender,
                        binding.etNickname.getText().toString(),
                        binding.etSignature.getText().toString()
                );
            }
        });

    }

    @Override
    protected void initData() {
        viewModel.getUserProfile();
    }

    //更新个人信息ui
    private void updateUserInfoUI(UserProfile userProfile) {
        if (userProfile.getGender().equals("")) binding.tvSex.setText("女");
        else binding.tvSex.setText("男");
        binding.etNickname.setText(userProfile.getNikeName());
        binding.etSignature.setText(userProfile.getSignature());
    }
}