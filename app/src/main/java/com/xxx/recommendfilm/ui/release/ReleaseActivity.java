package com.xxx.recommendfilm.ui.release;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityReleaseBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;

public class ReleaseActivity extends BaseActivity<ActivityReleaseBinding, ReleaseViewModel> {
    public static final String KEY_IMG = "key_img";

    @Override
    protected Class<ReleaseViewModel> getViewModelClazz() {
        return ReleaseViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_release;
    }

    @Override
    protected void initView() {
        viewModel.imgUrlLiveData.setValue(getIntent().getStringExtra(KEY_IMG));
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {

    }
}
