package com.xxx.recommendfilm.ui.register;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.Observer;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityRegisterBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;
import com.xxx.recommendfilm.ui.home.MainActivity;
import com.xxx.recommendfilm.ui.login.LoginActivity;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    @Override
    protected Class<RegisterViewModel> getViewModelClazz() {
        return RegisterViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        viewModel.isRegisterSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }

            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.registerAndLogin();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

}