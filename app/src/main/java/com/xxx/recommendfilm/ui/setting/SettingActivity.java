package com.xxx.recommendfilm.ui.setting;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivitySettingBinding;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.ui.base.BaseActivity;
import com.xxx.recommendfilm.ui.util.PictureSelectUtil;
import com.xxx.recommendfilm.util.PermissionUtil;
import com.zhihu.matisse.Matisse;
import io.reactivex.functions.Action;


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

        viewModel.genderLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("F")) binding.tvSex.setText("女");
                else binding.tvSex.setText("男");
            }
        });

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.llAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtil.rxCheckPermission(SettingActivity.this, new Action() {
                    @Override
                    public void run() {
                        PictureSelectUtil.showAvatarAlbum(SettingActivity.this);
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe();
            }
        });

        binding.llBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtil.rxCheckPermission(SettingActivity.this, new Action() {
                    @Override
                    public void run() {
                        PictureSelectUtil.showAvatarAlbum(SettingActivity.this);
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe();

            }
        });

        binding.llSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.genderLiveData.getValue().equals("F"))
                    viewModel.genderLiveData.postValue("M");
                else viewModel.genderLiveData.postValue("F");
            }
        });

        binding.llBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 /*               Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(SettingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //binding.tvBirthday.setText(calendar.get(Calendar.YEAR));
                    }
                }
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));*/
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.updateUserProfile(
                        "https://w.wallhaven.cc/full/39/wallhaven-39pw6v.jpg",
                        "https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/08/07/ChMkJl3FGRKIKoLpAAZs15rr_MoAAvHKAOi34gABmzv861.jpg",
                        25316412647l
                );
                finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureSelectUtil.REQUESTCODE_AVATAR) {
                assert data != null;
                viewModel.avatarLocalUriLiveData.postValue(Matisse.obtainResult(data).get(0));
            } else if (requestCode == PictureSelectUtil.REQUESTCODE_BACKGROUND) {
                assert data != null;
                viewModel.backgroundLocalUrlLiveData.postValue(Matisse.obtainResult(data).get(0));
            }
        }

    }
}